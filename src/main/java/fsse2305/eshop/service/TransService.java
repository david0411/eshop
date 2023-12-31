package fsse2305.eshop.service;

import fsse2305.eshop.data.data.FinishTransResponseData;
import fsse2305.eshop.data.data.GetTransResponseData;
import fsse2305.eshop.data.data.PayTransResponseData;
import fsse2305.eshop.data.data.PrepareTransResponseData;
import fsse2305.eshop.user.FirebaseUserData;

public interface TransService {
    PrepareTransResponseData prepareTrans(FirebaseUserData firebaseUserData) throws Exception;

    GetTransResponseData getTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception;

    PayTransResponseData payTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception;

    FinishTransResponseData finishTrans(Integer tid, FirebaseUserData firebaseUserData) throws Exception;
}
