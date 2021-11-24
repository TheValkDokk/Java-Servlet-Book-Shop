
package dao;

public class CartDTO {
    private int id;
    private String bookID;
    private int quantity;
    private float price;
    private int orderID;
    private bookDTO book;

    public CartDTO() {
    }

    public CartDTO(int id, String bookID, int quantity, float price, int orderID, bookDTO book) {
        this.id = id;
        this.bookID = bookID;
        this.quantity = quantity;
        this.price = price;
        this.orderID = orderID;
        this.book = book;
    }

    public bookDTO getBook() {
        return book;
    }

    public void setBook(bookDTO book) {
        this.book = book;
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
