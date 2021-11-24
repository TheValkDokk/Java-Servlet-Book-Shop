
package dao;

public class CartDTO {
    private int id;
    private String bookID;
    private int quantity;
    private float price;
    private int orderID;

    public CartDTO() {
    }

    public CartDTO(int id, String bookID, int quantity, float price, int orderID) {
        this.id = id;
        this.bookID = bookID;
        this.quantity = quantity;
        this.price = price;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
}
