//package com.dev.controllers;
//
//import com.dev.objects.User;
//import com.dev.responses.BasicResponse;
//import com.dev.utils.Constants;
//import com.dev.utils.Persist;
//import com.dev.utils.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//import static com.dev.utils.Errors.*;
//import static com.dev.utils.Errors.ERROR_MISSING_USERNAME;
//
//@RestController
//public class OfferController {
//
//    @Autowired
//    private Utils utils;
//
//    @Autowired
//    private Persist persist;
//
//    @RequestMapping(value = "add-offer")
//    public BasicResponse uploadOffer (String token, int itemId, int amount) {
//        BasicResponse basicResponse = new BasicResponse();
//        int payForSys = 1;
//        List<Offer> offerList = persist.getAllOffers();
//        int max = offerList.get(0).getAmount();
//        int lowNum= 0;
//        boolean low = false;
//        boolean success = false;
//        Integer errorCode = null;
//        int userCredAfterOffer;
//        User user = persist.getUserByToken(token);
//        if (user != null) {
//            if (amount > Constants.ZERO) {
//                if (user.getCred() > amount) {
//                    for (Offer i: offerList) {
//                        if (user.getId() == i.getUserId() && i.getItemId() == itemId) {
//                            if (max > i.getAmount()) {
//
//                            }else{
//                                max =i.getAmount();
//                            }
//                        }
//                    }
//                    if (max>amount) {
//
//                    }else {
//                        int number = user.getCred() + max;
//                        userCredAfterOffer = number - amount;
//                        Offer offerToAdd = new Offer(user.getId(), itemId, amount);
//                        persist.addOfferToDb(offerToAdd);
//                        userCredAfterOffer = userCredAfterOffer - 1;
//                        System.out.println(userCredAfterOffer);
//                        persist.updateSysTotal(payForSys);
//                        persist.updateCredAfterOffer(token, userCredAfterOffer);
//                        success = true;
//                    }
//
//                } else {
//                    errorCode = ERROR_WEAK_PASSWORD;
//                }
//            } else {
//                errorCode = ERROR_MISSING_PASSWORD;
//            }
//        } else {
//            errorCode = ERROR_MISSING_USERNAME;
//        }
//        basicResponse.setSuccess(success);
//        basicResponse.setErrorCode(errorCode);
//        return basicResponse;
//    }
//
//
//
//
//
//
//}
