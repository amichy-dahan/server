package com.dev.controllers;
import com.dev.objects.Price;
import com.dev.objects.Salary;
import com.dev.responses.AllSalaryResponse;
import com.dev.responses.BasicResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;


        @RequestMapping(value = "add-line")
        public BasicResponse uploadLineWork (String costumer, String date, String location, int employeeId,int hours ,int kilometer ) {
                     Price price = persist.getUserById(employeeId);
                     int workPrice =hours * price.getPriceOfHour() ;
                     double drivePrice = kilometer * price.getPriceOfKilo();
                     double totalPrice = workPrice + drivePrice;
                     Salary salary = new Salary(costumer,location,date,hours,kilometer,employeeId, (int) drivePrice,workPrice, (int) totalPrice);
                     persist.addSalaryToDb(salary);
            return new BasicResponse();
    }
    @RequestMapping(value = "add-price")
    public BasicResponse uploadPrice (int employeeId , int priceOfHour , double priceOfKilo) {
        Price price = persist.getUserById(employeeId);
        if (price!=null){
            persist.updatePrices(employeeId,priceOfHour,priceOfKilo);
        }else {
            Price price1 = new Price(employeeId,priceOfHour,priceOfKilo);
            persist.addPriceToDb(price1);
        }
        return new BasicResponse(true,0);
    }


    @RequestMapping(value = "/get-prices", method = {RequestMethod.GET, RequestMethod.POST})
    public Price getAllPrices (int employeeId) {
        Price price = persist.getUserById(employeeId);
        return price;
    }
    @RequestMapping(value = "/get-salary", method = {RequestMethod.GET, RequestMethod.POST})
    public BasicResponse getAllSalary(int employeeId) {
        List <Salary> salaries = persist.getAllSalary(employeeId);
        BasicResponse basicResponse = new AllSalaryResponse(true,0,salaries);
        return basicResponse;
    }

    @RequestMapping(value = "/get-final-sal", method = {RequestMethod.GET, RequestMethod.POST})
    public int getFinalSalary(int employeeId) {
        List <Salary> salaries = persist.getAllSalary(employeeId);
        int sum = 0;
        for (int i = 0; i < salaries.size(); i++) {
            sum += salaries.get(i).getTotalPrice();
        }
        return sum ;
    }

    @RequestMapping(value = "update-sal")
    public BasicResponse updateSalary (int employeeId , int priceOfHour , double priceOfKilo) {
        Price price = persist.getUserById(employeeId);
        if (price!=null){
            persist.updatePrices(employeeId,priceOfHour,priceOfKilo);
        }else {
            Price price1 = new Price(employeeId,priceOfHour,priceOfKilo);
            persist.addPriceToDb(price1);
        }
        return new BasicResponse(true,0);
    }





}















































//    @RequestMapping(value = "add-item")
//    public BasicResponse uploadItem (String token, String itemName, String description, int price, String picLink) {
//        BasicResponse basicResponse = new BasicResponse();
//        int costOfferSys = 2;
//        String openOrClose = "open";
//        boolean success = false;
//        Integer errorCode = null;
//        User user = persist.getUserByToken(token);
//        if (itemName != null) {
//            if (description != null) {
//                if (price >= Constants.MINIMAL_PRICE) {
//                    if (picLink != null) {
//                        Item itemToAdd = new Item(user.getId(), itemName, description, price, picLink,openOrClose);
//                        persist.addItemToDB(itemToAdd);
//                        int costOffer = user.getCred()-2;
//                        persist.updateSysTotal(costOfferSys);
//                        persist.updateCredAfterOffer(token,costOffer);
//                        success = true;
//                    } else {
//                        errorCode = ERROR_CODE_EXISTING_USER;
//                    }
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
//    @RequestMapping (value = "get-items" , method = RequestMethod.GET)
//    public List<Item> getItems (String token) {
//        List<Item>items = new ArrayList<>();
//        User user = persist.getUserByToken(token);
//        if (user!=null) {
//            List<Item> allItems = persist.getAllItems();
//             items = allItems.stream().filter(item -> item.getOffer() != user.getId())
//                    .collect(Collectors.toList());
//        } else {
//
//        }
//        return items;
//    }
//
//
//    @RequestMapping (value = "close-offer" , method = {RequestMethod.GET,RequestMethod.POST} )
//    public Offer getWinOffer (String token ,int itemId) {
//        User user =persist.getUserByToken(token);
//        Item item =persist.getItemById(itemId);
//        int payForUser;
//        int afterCal;
//        int payForSys;
//        String closeOffer = "close";
//        Boolean success = false;
//        List<Offer> offerListAfterFilterByItem = persist.getAllOffers()
//                .stream().filter(offer -> offer.getItemId() == itemId)
//                .collect(Collectors.toList());
//        Offer winner = null;
//            if (offerListAfterFilterByItem.size()!=0 && Objects.equals(item.getOpenOrClose(), "open")) {
//                 winner = offerListAfterFilterByItem.get(0);
//                if (offerListAfterFilterByItem.size()>= 3) {
//                    for (Offer i : offerListAfterFilterByItem) {
//                        if (i.getAmount() > winner.getAmount()) {
//                            winner = i;
//                        }
//                        if (i.getAmount() == winner.getAmount()) {
//                            if (i.getOfferDate().before(winner.getOfferDate())) ;
//                            winner = i;
//                        }
//                    }
//                    afterCal = user.getCred()+winner.getAmount();//4118-4090.5
//                    float percent = 0.05F;
//                    payForUser = (int) (afterCal-(winner.getAmount()*percent));
//                    payForSys = afterCal - payForUser;//28
//                    persist.updateSysTotal(payForSys);
//                    persist.updateCredAfterOffer(token , payForUser);
//                    persist.updateItems(closeOffer,itemId);
//                }else {
//                    System.out.println("error");
//                    winner = null;
//
//                }
//            }
//
//            return winner;
//    }
//
//
//
//
//
//
//}
