package dao;

public class bookDTO {
    private String id;
    private String name;
    private float price;
    private int quantity;
    private String cateID;
    private String img;
    private String dercs;
    private String cover;
    private String write;
    private String pen;
    private String pub;
    private boolean status;

    public bookDTO(String id, String name, float price, int quantity, String cateID, String img, String dercs, String cover, String write, String pen, String pub, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cateID = cateID;
        this.img = img;
        this.dercs = dercs;
        this.cover = cover;
        this.write = write;
        this.pen = pen;
        this.pub = pub;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDercs() {
        return dercs;
    }

    public void setDercs(String dercs) {
        this.dercs = dercs;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public String getPen() {
        return pen;
    }

    public void setPen(String pen) {
        this.pen = pen;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "bookDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", cateID=" + cateID + ", img=" + img + ", dercs=" + dercs + ", cover=" + cover + ", write=" + write + ", pen=" + pen + ", pub=" + pub + ", status=" + status + '}';
    }

    
    
}
