package fsse2305.eshop.service.impl;

import fsse2305.eshop.data.data.*;
import fsse2305.eshop.data.entity.CartItemEntity;
import fsse2305.eshop.data.entity.ProductEntity;
import fsse2305.eshop.data.entity.TransProductEntity;
import fsse2305.eshop.data.entity.TransactionEntity;
import fsse2305.eshop.data.transactionEnum.TransStatus;
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
import java.time.LocalDateTime;
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
            Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
            BigDecimal totalAmt = BigDecimal.valueOf(0);
            List<CartItemEntity> cartItemEntityList = cartItemService.getCartItemByUid(uid);
            if(cartItemEntityList.size() == 0)  {
                //cart empty
                return null;
            }
            Timestamp transactionTime = Timestamp.valueOf(LocalDateTime.now());
            Integer transResult = transRepository.createTransaction(uid, transactionTime, TransStatus.PREPARE.getCode());
            TransactionEntity transactionEntity = transRepository.getTransactionEntityByTransTime(transactionTime);
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (CartItemEntity cartItemEntity: cartItemService.getCartItemByUid(uid))  {
                ProductEntity productEntity = cartItemEntity.getProductEntity();
                BigDecimal subtotal = productEntity.getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity()));
                totalAmt = totalAmt.add(subtotal);
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
                TransProductEntity transProductEntity = transProductRepository.getTransactionEntityByPidAndTid(cartItemEntity.getPid(), transactionEntity.getTid());
                ProductResponseData productResponseData = new ProductResponseData(transProductEntity);
                TransProductResponseData transProductResponseData = new TransProductResponseData(transProductEntity, productResponseData);
                transProductResponseDataList.add(transProductResponseData);
            }
            transactionEntity.setTotal(totalAmt);
            transRepository.save(transactionEntity);

            return new PrepareTransResponseData(transactionEntity, transProductResponseDataList);
        }   catch (Exception e) {
            logger.warn(e.toString());
            throw e;
        }
    }

    public GetTransResponseData getTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception {
        Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
        TransactionEntity transactionEntity = transRepository.getTransactionEntityByUidAndTid(uid, tid);
        List<TransProductEntity> transProductEntityList = transProductRepository.getTransactionEntityByTid(transactionEntity.getTid());
        List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
        for (TransProductEntity transProductEntity: transProductEntityList)  {
            ProductResponseData productResponseData = new ProductResponseData(transProductEntity);
            TransProductResponseData transProductResponseData = new TransProductResponseData(transProductEntity, productResponseData);
            transProductResponseDataList.add(transProductResponseData);
        }
        return new GetTransResponseData(transactionEntity, transProductResponseDataList);
    }

    public PayTransResponseData payTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception {
        Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
        Integer payResult = transRepository.updateTransStatusByUidAndTid(uid, tid, TransStatus.PAY.getCode());
        if(payResult==1)   {
            for(TransProductEntity transProductEntity: transProductRepository.getTransactionEntityByTid(tid))   {
                Integer deductProductResult = productService.deductProductQtyById(transProductEntity.getPid(), transProductEntity.getQuantity());
                if(deductProductResult==1)  {
                    Integer processResult = transRepository.updateTransStatusByUidAndTid(uid, tid, TransStatus.PROCESSING.getCode());
                    if(processResult==1)   {
                        return new PayTransResponseData("SUCCESS");
                    }
                }
            }
        }
        return new PayTransResponseData("FAIL");
    }

    public FinishTransResponseData finishTrans(Integer tid, FirebaseUserData firebaseUserData) {
        Integer uid = userService.getEntityByFirebaseUserData(firebaseUserData).getUid();
        Integer result = transRepository.updateTransStatusByUidAndTid(uid, tid, TransStatus.SUCCESS.getCode());
        if(result==1)   {
            TransactionEntity transactionEntity = transRepository.getTransactionEntityByUidAndTid(uid, tid);
            List<TransProductEntity> transProductEntityList = transProductRepository.getTransactionEntityByTid(transactionEntity.getTid());
            List<TransProductResponseData> transProductResponseDataList = new ArrayList<>();
            for (TransProductEntity transProductEntity: transProductEntityList)  {
                ProductResponseData productResponseData = new ProductResponseData(transProductEntity);
                TransProductResponseData transProductResponseData = new TransProductResponseData(transProductEntity, productResponseData);
                transProductResponseDataList.add(transProductResponseData);
            }
            cartItemService.deleteCartItemByUid(uid);
            return new FinishTransResponseData(transactionEntity, transProductResponseDataList);
        }
        //throw error
        return null;
    }
}
