package fsse2305.eshop.api;

import fsse2305.eshop.data.data.FinishTransResponseData;
import fsse2305.eshop.data.data.GetTransResponseData;
import fsse2305.eshop.data.data.PrepareTransResponseData;
import fsse2305.eshop.data.data.TransProductResponseData;
import fsse2305.eshop.data.dto.*;
import fsse2305.eshop.service.TransService;
import fsse2305.eshop.user.FirebaseUserData;
import fsse2305.eshop.utility.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransApi {
    public final TransService transService;

    public TransApi(TransService transService) {
        this.transService = transService;
    }

    @PostMapping("/prepare")
    public PrepareTransResponseDto prepareTrans(JwtAuthenticationToken jwtToken) throws Exception {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        PrepareTransResponseData prepareTransResponseData = transService.prepareTrans(firebaseUserData);
        List<TransProductResponseDto> transProductResponseDtoList = new ArrayList<>();
        for(TransProductResponseData transProductResponseData: prepareTransResponseData.getTransProductResponseDataList())  {
            transProductResponseDtoList.add(new TransProductResponseDto(transProductResponseData,new ProductResponseDto(transProductResponseData.getProductResponseData())));
        }
        return new PrepareTransResponseDto(prepareTransResponseData, transProductResponseDtoList);
    }

    @GetMapping("/{tid}")
    public GetTransResponseDto getTransById(@PathVariable Integer tid, JwtAuthenticationToken jwtToken) throws Exception {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        GetTransResponseData getTransResponseData = transService.getTrans(tid, firebaseUserData);
        List<TransProductResponseDto> transProductResponseDtoList = new ArrayList<>();
        for(TransProductResponseData transProductResponseData: getTransResponseData.getTransProductResponseDataList())  {
            transProductResponseDtoList.add(new TransProductResponseDto(transProductResponseData,new ProductResponseDto(transProductResponseData.getProductResponseData())));
        }
        return new GetTransResponseDto(getTransResponseData, transProductResponseDtoList);
    }

    @PatchMapping("/{tid}/pay")
    public PayTransResponseDto payTrans(@PathVariable Integer tid, JwtAuthenticationToken jwtToken) throws Exception {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new PayTransResponseDto(transService.payTrans(tid, firebaseUserData));
    }
    @PatchMapping("/{tid}/finish")
    public FinishTransResponseDto finishTransById(@PathVariable Integer tid, JwtAuthenticationToken jwtToken) throws Exception {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        FinishTransResponseData finishTransResponseData = transService.finishTrans(tid, firebaseUserData);
        List<TransProductResponseDto> transProductResponseDtoList = new ArrayList<>();
        for(TransProductResponseData transProductResponseData: finishTransResponseData.getTransProductResponseDataList())  {
            transProductResponseDtoList.add(new TransProductResponseDto(transProductResponseData,new ProductResponseDto(transProductResponseData.getProductResponseData())));
        }
        return new FinishTransResponseDto(finishTransResponseData, transProductResponseDtoList);
    }

}
