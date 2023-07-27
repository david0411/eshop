package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.*;
import fsse2305.eshop.data.entity.CartItemEntity;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.data.entity.TransProductEntity;
import fsse2305.eshop.data.entity.TransactionEntity;
import fsse2305.eshop.data.transactionEnum.TransStatus;
import fsse2305.eshop.exception.cart.CART_EMPTTY_EXCEPTION;
import fsse2305.eshop.exception.general.DATA_INSUFFICIENT_EXCEPTION;
import fsse2305.eshop.exception.transaction.*;
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
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransServiceImpl implements TransService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
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
            logger.info("Start prepare transaction.");
            if(firebaseUserData == null) throw new DATA_INSUFFICIENT_EXCEPTION();
            Integer uid = getUid(firebaseUserData);
            BigDecimal totalAmt = BigDecimal.valueOf(0);
            List<CartItemEntity> cartItemEntityList = cartItemService.getCartItemByUid(uid);
            if(cartItemEntityList.size() == 0) throw new CART_EMPTTY_EXCEPTION();
            Timestamp transactionTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
            logger.info("Create transaction. uid:" + uid + " transactionTime:" + transactionTime);
            Integer transResult = transRepository.createTransaction(uid, transactionTime, TransStatus.PREPARE.getCode());
            if(transResult!=1) throw new TRANSACTION_CREATE_FAILED_EXCEPTION();
            logger.info("Get transaction by time. transactionTime:" + transactionTime);
            TransactionEntity transactionEntity = transRepository.getTransactionEntityByTransTime(transactionTime);
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (CartItemEntity cartItemEntity: cartItemEntityList)  {
                ProductEntity productEntity = cartItemEntity.getProductEntity();
                BigDecimal subtotal = productEntity.getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity()));
                totalAmt = totalAmt.add(subtotal);
                logger.info("Add transaction product. tid:" + transactionEntity.getTid() + " pid:" + productEntity.getPid());
                Integer transProductResult = transProductRepository.createTransProduct(
                        transactionEntity.getTid(),
                        productEntity.getPid(),
                        productEntity.getName(),
                        productEntity.getDescription(),
                        productEntity.getImageUrl(),
                        productEntity.getPrice(),
                        productEntity.getStockQty(),
                        cartItemEntity.getQuantity(),
                        subtotal);
                if(transProductResult!=1) throw new TRANSACTION_ADD_ITEM_FAILED_EXCEPTION(productEntity.getName());
                TransProductEntity transProductEntity = transProductRepository.getTransactionProductEntityByPidAndTid(cartItemEntity.getPid(), transactionEntity.getTid());
                transProductResponseDataList.add(
                        new TransProductResponseData(
                                transProductEntity,
                                new ProductResponseData(transProductEntity)
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

    public GetTransResponseData getTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception {
        try {
            logger.info("Start get transaction.");
            if(tid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            logger.info("Get transaction. uid:" + uid + " tid:" + tid);
            TransactionEntity transactionEntity = transRepository.getTransactionEntityByUidAndTid(uid, tid);
            if(transactionEntity == null)   {
                throw new TRANSACTION_NOT_FOUND_EXCEPTION(tid);
            }
            logger.info("Get transaction product. tid:" + tid);
            List<TransProductEntity> transProductEntityList = transProductRepository.getTransactionProductEntityByTid(transactionEntity.getTid());
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (TransProductEntity transProductEntity: transProductEntityList)  {
                transProductResponseDataList.add(
                        new TransProductResponseData(
                                transProductEntity,
                                new ProductResponseData(transProductEntity
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

    public PayTransResponseData payTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception {
        try {
            logger.info("Start pay transaction.");
            if(tid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            logger.info("Get transaction. uid:" + uid + " tid:" + tid);
            TransactionEntity transactionEntity = transRepository.getTransactionEntityByUidAndTid(uid, tid);
            checkTransStatus(transactionEntity,TransStatus.PREPARE);
            if(updateTransStatus(transactionEntity, TransStatus.PAY)==1)   {
                for(TransProductEntity transProductEntity: transProductRepository.getTransactionProductEntityByTid(tid))   {
                    logger.info("Deduct stock qty");
                    Integer deductProductResult = productService.deductProductQtyByPid(transProductEntity.getPid(), transProductEntity.getQuantity());
                    if(deductProductResult==1)  {
                        //checkTransStatus(transactionEntity,TransStatus.PAY);
                        if(updateTransStatus(transactionEntity, TransStatus.PROCESSING)==1)   {
                            return new PayTransResponseData("SUCCESS");
                        }
                    }
                }
            }
            return new PayTransResponseData("FAIL");
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public FinishTransResponseData finishTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception{
        try {
            logger.info("Start finish transaction.");
            if(tid == null || firebaseUserData == null) {
                throw new DATA_INSUFFICIENT_EXCEPTION();
            }
            Integer uid = getUid(firebaseUserData);
            logger.info("Get transaction. uid:" + uid + " tid:" + tid);
            TransactionEntity transactionEntity = transRepository.getTransactionEntityByUidAndTid(uid, tid);
            checkTransStatus(transactionEntity,TransStatus.PROCESSING);
            if(updateTransStatus(transactionEntity, TransStatus.SUCCESS)==1)   {
                logger.info("Get transaction product. tid:" + tid);
                List<TransProductEntity> transProductEntityList = transProductRepository.getTransactionProductEntityByTid(transactionEntity.getTid());
                List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
                for (TransProductEntity transProductEntity: transProductEntityList)  {
                    ProductResponseData productResponseData = new ProductResponseData(transProductEntity);
                    TransProductResponseData transProductResponseData = new TransProductResponseData(transProductEntity, productResponseData);
                    transProductResponseDataList.add(transProductResponseData);
                }
                logger.info("Empty Cart. uid:" + uid);
                Integer deleteCartResult = cartItemService.deleteCartItemByUid(uid);
                logger.info(deleteCartResult + "rows deleted from cart");
                return new FinishTransResponseData(transactionEntity, transProductResponseDataList);
            }
            throw new TRANSACTION_FINISH_FAILED_EXCEPTION(tid);
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    private Integer getUid(FirebaseUserData firebaseUserData)   {
        logger.info("Get User");
        return userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
    }

    private void checkTransStatus(TransactionEntity transactionEntity, TransStatus transStatus) throws TRANSACTION_STATUS_ERROR_EXCEPTION {
        try {
            logger.info("Check transaction status");
            if(!transactionEntity.getStatus().equals(transStatus))   {
                throw new TRANSACTION_STATUS_ERROR_EXCEPTION(transactionEntity.getStatus().getCode(),transStatus.getCode());
            }
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    private Integer updateTransStatus(TransactionEntity transactionEntity, TransStatus transStatus)  {
        logger.info("Update transaction status. tid:" + transactionEntity.getTid() + " status:" + transStatus.getCode());
        return transRepository.updateTransStatusByUidAndTid(transactionEntity.getUserEntity().getUid(), transactionEntity.getTid(), transStatus.getCode());
    }
}
