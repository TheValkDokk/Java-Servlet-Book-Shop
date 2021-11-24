package dao;

public class OrderDTO {
    private int id;
    private String userID;
    private String devAddr;
    private String paymentCheck;
    private int total;

    public OrderDTO() {
    }

    public OrderDTO(int id, String userID, String devAddr, String paymentCheck, int total) {
        this.id = id;
        this.userID = userID;
        this.devAddr = devAddr;
        this.paymentCheck = paymentCheck;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
}
