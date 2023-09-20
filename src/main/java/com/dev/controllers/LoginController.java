package com.dev.controllers;


import com.dev.objects.Employee;

import com.dev.responses.*;
import com.dev.utils.Constants;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.utils.Errors.*;

@RestController
public class LoginController {

    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @RequestMapping(value = "sign-up" ,method = { RequestMethod.POST})
    public BasicResponse signUp (String email, String password , String firstName, String lastName, String socialSecurityNumber, String address , int admin) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (email != null) {
            if (password != null) {
                if (utils.isStrongPassword(password)) {
                    Employee fromDb = persist.getUserByEmail(email);
                    if (fromDb == null) {
                        Employee userToAdd = new Employee(email , utils.createHash(email, password),firstName,lastName,socialSecurityNumber,address ,admin);
                        persist.addEmployeeToDb(userToAdd);
                        success = true;
                    } else {
                        errorCode = ERROR_CODE_EXISTING_USER;
                    }
                } else {
                    errorCode = ERROR_WEAK_PASSWORD;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }


    @RequestMapping(value = "sign-up-admin" ,method = { RequestMethod.POST})
    public BasicResponse signUpAdmin (String email, String password , int admin) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (email != null) {
            if (password != null) {
                if (utils.isStrongPassword(password)) {
                    Employee fromDb = persist.getUserByEmail(email);
                    if (fromDb == null) {
                        Employee userToAdd = new Employee(email , utils.createHash(email, password),admin);
                        persist.addEmployeeToDb(userToAdd);
                        success = true;
                    } else {
                        errorCode = ERROR_CODE_EXISTING_USER;
                    }
                } else {
                    errorCode = ERROR_WEAK_PASSWORD;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

    @RequestMapping(value ="/login" ,method = {RequestMethod.GET, RequestMethod.POST})
    public BasicResponse Login(String email, String password) {
        System.out.println(email);
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (email != null) {
            if (password != null) {
                String token = utils.createHash(email,password);
                Employee emailDb = persist.getUserByEmail(email);
                Employee fromDb = persist.getEmployeeByEmployeeGmailAndToken(email, token);
              if (fromDb != null) {
                  success = true;
                  System.out.println(success);
                  basicResponse = new LoginResponse(fromDb.getEmail(), fromDb.getFirstName(),fromDb.getAdmin(), fromDb.getId());
              } else if (emailDb !=null){
                  basicResponse = new LoginResponse(emailDb.getEmail(),emailDb.getFirstName(),fromDb.getAdmin(), fromDb.getId());
              }else {
                  errorCode = ERROR_WRONG_LOGIN_CREDS;
              }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

//    @RequestMapping(value = "/get-all-users", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse getAllUsers () {
//        List<User> allUsers = persist.getAllUsers();
//        AllUsersResponse allUsersResponse = new AllUsersResponse(true,null,allUsers);
//        return allUsersResponse;
//    }
//
//    @RequestMapping(value = "/get-all-tenders", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse getAllTenders() {
//        List<Item> items = persist.getAllItems();
//        int max;
//        AllTenderResponse allTenderResponse= new AllTenderResponse(items);
//        List<ItemModel> list = allTenderResponse.getItemsList();
//        for (ItemModel itemModel:list) {
//            max = persist.getOffersById(itemModel.getId());
//            itemModel.setBigOffer(max);
//        }
//        AllTenderResponse allTenderResponse1 = new AllTenderResponse(true,null,list);
//        return allTenderResponse1;
//    }
//

//
//
    @RequestMapping(value = "/date", method = {RequestMethod.GET, RequestMethod.POST})
    public void getUserCred (String date) {
        System.out.println(date);
    }

    @RequestMapping(value = "/check-email", method = {RequestMethod.GET, RequestMethod.POST})
    public Employee getEmail (String email) {
        Employee user = persist.getUserByEmail(email);
        return user;
    }
    @RequestMapping(value = "/login-admin", method = {RequestMethod.GET, RequestMethod.POST})
    public Employee getLogin (String email, String password) {
        String token = utils.createHash(email,password);
        Employee user = persist.getUserByToken(token);
        return user;
    }
}
