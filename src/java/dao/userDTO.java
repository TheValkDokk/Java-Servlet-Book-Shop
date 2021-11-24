
package dao;

public class userDTO {

    public userDTO() {
    }
    private String id;
    private String password;
    private String name;
    private String roleID;
    private int phone;
    private String addr;

    public userDTO(String id, String password, String name, String roleID, int phone, String addr) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.roleID = roleID;
        this.phone = phone;
        this.addr = addr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "userDTO{" + "id=" + id + ", password=" + password + ", name=" + name + ", roleID=" + roleID + ", phone=" + phone + ", addr=" + addr + '}';
    }
    
    
}
