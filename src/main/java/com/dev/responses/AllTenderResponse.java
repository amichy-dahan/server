//package com.dev.responses;
//
//import com.dev.models.ItemModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AllTenderResponse extends BasicResponse{
//
//    private List<ItemModel> itemsList;
//
//
//public AllTenderResponse(List<Item> items){
//    this.setSuccess(true);
//    this.itemsList = new ArrayList<>();
//    for (Item item: items ){
//      this.itemsList.add(new ItemModel(item));
//    }
//
//}
//
//    public AllTenderResponse(boolean success, Integer errorCode, List<ItemModel> itemsList) {
//        super(success, errorCode);
//        this.itemsList = itemsList;
//    }
//
//
//    public List<ItemModel> getItemsList() {
//        return itemsList;
//    }
//
//    public void setItemsList(List<ItemModel> itemsList) {
//        this.itemsList = itemsList;
//    }
//}
