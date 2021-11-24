package dao;

import java.util.List;

public class OrderDTO {
    private int id;
    private String userID;
    private String bookList;
    private String devAddr;
    private String paymentCheck;
    private int status;

    public OrderDTO() {
    }

    public OrderDTO(int id, String userID, String bookList, String devAddr, String paymentCheck, int status) {
        this.id = id;
        this.userID = userID;
        this.bookList = bookList;
        this.devAddr = devAddr;
        this.paymentCheck = paymentCheck;
        this.status = status;
    }

    public String getPaymentCheck() {
        return paymentCheck;
    }

    public void setPaymentCheck(String paymentCheck) {
        this.paymentCheck = paymentCheck;
    }

    

    public String getDevAddr() {
        return devAddr;
    }

    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBookList() {
        return bookList;
    }

    public void setBookList(String bookList) {
        this.bookList = bookList;
    }

   
    
    
}
