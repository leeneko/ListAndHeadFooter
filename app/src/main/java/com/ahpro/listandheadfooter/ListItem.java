package com.ahpro.listandheadfooter;

public class ListItem {
    private String DocNum;
    private String ItemCode;
    private String ItemName;
    private String PostDate;
    private String DueDate;
    private String Quantity;
    private String UOM;
    private String CmplQty;
    private String Remarks;

    public ListItem(String docNum, String itemCode, String itemName, String postDate, String dueDate, String quantity, String UOM, String cmplQty, String remarks) {
        DocNum = docNum;
        ItemCode = itemCode;
        ItemName = itemName;
        PostDate = postDate;
        DueDate = dueDate;
        Quantity = quantity;
        this.UOM = UOM;
        CmplQty = cmplQty;
        Remarks = remarks;
    }

    public String getDocNum() {
        return DocNum;
    }

    public void setDocNum(String docNum) {
        DocNum = docNum;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPostDate() {
        return PostDate;
    }

    public void setPostDate(String postDate) {
        PostDate = postDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getCmplQty() {
        return CmplQty;
    }

    public void setCmplQty(String cmplQty) {
        CmplQty = cmplQty;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
