package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.*;
import fsse2305.eshop.data.entity.CartItemEntity;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.data.entity.TransProductEntity;
import fsse2305.eshop.data.entity.TransactionEntity;
import fsse2305.eshop.data.transactionEnum.TransStatus;
import fsse2305.eshop.exception.cart.CART_EMPTTY_EXCEPTION;
import fsse2305.eshop.exception.general.DATA_INSUFFICIENT_EXCEPTION;
import fsse2305.eshop.exception.transaction.TRANSACTION_NOT_FOUND_EXCEPTION;
import fsse2305.eshop.exception.transaction.TRANSACTION_STATUS_ERROR_EXCEPTION;
import fsse2305.eshop.repository.TransProductRepository;
import fsse2305.eshop.repository.TransRepository;
import fsse2305.eshop.service.CartItemService;
import fsse2305.eshop.service.ProductService;
import fsse2305.eshop.service.TransService;
import fsse2305.eshop.service.UserService;
import fsse2305.eshop.user.FirebaseUserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransServiceImpl implements TransService {
    final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final TransRepository transRepository;
    private final TransProductRepository transProductRepository;

    public TransServiceImpl(UserService userService, ProductService productService, CartItemService cartItemService, TransRepository transRepository, TransProductRepository transProductRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.transRepository = transRepository;
        this.transProductRepository = transProductRepository;
    }

    public PrepareTransResponseData prepareTrans(FirebaseUserData firebaseUserData) throws Exception{
        try {
            if(firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            BigDecimal totalAmt = BigDecimal.valueOf(0);
            List<CartItemEntity> cartItemEntityList = cartItemService.getCartItemByUid(uid);
            if(cartItemEntityList.size() == 0)  {
                throw new CART_EMPTTY_EXCEPTION();
            }
            LocalDateTime transactionTime = ZonedDateTime.now().toLocalDateTime();
            logger.info("Create transaction. uid:" + uid + " transactionTime:" + transactionTime);
            TransactionEntity newTransactionEntity = new TransactionEntity(uid, transactionTime, TransStatus.PREPARE.toString(), BigDecimal.ZERO);
            TransactionEntity transactionEntity = transRepository.save(newTransactionEntity);
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (CartItemEntity cartItemEntity: cartItemEntityList)  {
                ProductEntity productEntity = productService.getProductEntityByPid(cartItemEntity.getPid());
                BigDecimal subtotal = productEntity.getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity()));
                totalAmt = totalAmt.add(subtotal);
                logger.info("Add transaction product. tid:" + transactionEntity.getTid() + " pid:" + productEntity.getPid());
                TransProductEntity newTransProductEntity = new TransProductEntity(
                        transactionEntity.getTid(),
                        productEntity.getPid(),
                        productEntity.getName(),
                        productEntity.getDescription(),
                        productEntity.getImageUrl(),
                        productEntity.getPrice(),
                        productEntity.getStockQty(),
                        cartItemEntity.getQuantity(),
                        subtotal);
                transProductRepository.save(newTransProductEntity);
                transProductResponseDataList.add(
                        new TransProductResponseData(
                                newTransProductEntity,
                                new ProductResponseData(productEntity)
                        )
                );
            }
            logger.info("Update transaction totalAmt. tid:" + transactionEntity.getTid() + " totalAmt:" +totalAmt);
            transactionEntity.setTotal(totalAmt);
            transRepository.save(transactionEntity);
            return new PrepareTransResponseData(transactionEntity, transProductResponseDataList);
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public GetTransResponseData getTrans(String tid, FirebaseUserData firebaseUserData) throws Exception {
        try {
            if(tid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            logger.info("Get transaction. uid:" + uid + " tid:" + tid);
            TransactionEntity transactionEntity = transRepository.findByBuyerUidAndTid(uid, tid);
            if(transactionEntity == null)   {
                throw new TRANSACTION_NOT_FOUND_EXCEPTION(tid);
            }
            logger.info("Get transaction product. tid:" + tid);
            List<TransProductEntity> transProductEntityList = transProductRepository.findAllByTid(transactionEntity.getTid());
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (TransProductEntity transProductEntity: transProductEntityList)  {
                transProductResponseDataList.add(
                        new TransProductResponseData(
                                transProductEntity,
                                new ProductResponseData(productService.getProductEntityByPid(transProductEntity.getPid())
                                )
                        )
                );
            }
            return new GetTransResponseData(transactionEntity, transProductResponseDataList);
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public PayTransResponseData payTrans(String tid, FirebaseUserData firebaseUserData) throws Exception {
        try {
            if(tid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            logger.info("Get transaction. uid:" + uid + " tid:" + tid);
            TransactionEntity transactionEntity = transRepository.findByBuyerUidAndTid(uid, tid);
            logger.info("Check transaction status");
            if(!transactionEntity.getStatus().equals(TransStatus.PREPARE.toString()))   {
                throw new TRANSACTION_STATUS_ERROR_EXCEPTION(TransStatus.valueOf(transactionEntity.getStatus()),TransStatus.PREPARE);
            }
            logger.info("Update transaction status. tid:" + tid + " status:" + TransStatus.PAY.getCode());
            transactionEntity.setStatus(TransStatus.PAY.toString());
            transRepository.save(transactionEntity);
            for(TransProductEntity transProductEntity: transProductRepository.findAllByTid(tid))   {
                logger.info("Deduct stock qty");
                Integer deductProductResult = productService.deductProductQtyById(transProductEntity.getPid(), transProductEntity.getQuantity());
                if(deductProductResult==1)  {
                    logger.info("Check transaction status");
                    if(!transactionEntity.getStatus().equals(TransStatus.PAY.toString()))   {
                        throw new TRANSACTION_STATUS_ERROR_EXCEPTION(TransStatus.valueOf(transactionEntity.getStatus()),TransStatus.PAY);
                    }
                    logger.info("Update transaction status. tid:" + tid + " status:" + TransStatus.PROCESSING.getCode());
                    transactionEntity.setStatus(TransStatus.PROCESSING.toString());
                    transRepository.save(transactionEntity);
                    return new PayTransResponseData("SUCCESS");
                }
                //rollback
            }
            return new PayTransResponseData("FAIL");
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public FinishTransResponseData finishTrans(String tid, FirebaseUserData firebaseUserData) throws Exception{
        try {
            if(tid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            String uid = getUid(firebaseUserData);
            logger.info("Get transaction. uid:" + uid + " tid:" + tid);
            TransactionEntity transactionEntity = transRepository.findByBuyerUidAndTid(uid, tid);
            logger.info("Check transaction status");
            if(!transactionEntity.getStatus().equals(TransStatus.PROCESSING.toString()))   {
                throw new TRANSACTION_STATUS_ERROR_EXCEPTION(TransStatus.valueOf(transactionEntity.getStatus()),TransStatus.PROCESSING);
            }
            logger.info("Update transaction status. tid:" + tid + " status:" + TransStatus.SUCCESS.getCode());
            transactionEntity.setStatus(TransStatus.SUCCESS.toString());
            transRepository.save(transactionEntity);
            logger.info("Get transaction product. tid:" + tid);
            List<TransProductEntity> transProductEntityList = transProductRepository.findAllByTid(transactionEntity.getTid());
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (TransProductEntity transProductEntity: transProductEntityList)  {
                ProductResponseData productResponseData = new ProductResponseData(productService.getProductEntityByPid(transProductEntity.getPid()));
                TransProductResponseData transProductResponseData = new TransProductResponseData(transProductEntity, productResponseData);
                transProductResponseDataList.add(transProductResponseData);
            }
            logger.info("Empty Cart. uid:" + uid);
            Integer deleteCartResult = cartItemService.deleteCartItemByUid(uid);
            logger.info(deleteCartResult + "rows deleted from cart");
            return new FinishTransResponseData(transactionEntity, transProductResponseDataList);
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    private String getUid(FirebaseUserData firebaseUserData)   {
        logger.info("Get User");
        return userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
    }
}
