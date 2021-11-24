package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class bookDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public bookDAO() {
    }    
    
    public void addCart(CartDTO cart) {
        try {
            con = DBCon.getConnection();
            String sql = "insert into tblOrderDetail (detailID,productID,quantity,price,orderID)"
                    + " values(?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setInt(1, cart.getId());
            stm.setString(2, cart.getBookID());
            stm.setInt(3, cart.getQuantity());
            stm.setInt(3, cart.getOrderID());
            stm.setFloat(4, cart.getPrice());
            rs = stm.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public void updateCart(CartDTO cart) {
        try {
            con = DBCon.getConnection();
            String sql = "UPDATE tblOrderDetail"
                    + " SET productID = ?, quantity = ?, price = ?, orderID = ?"
                    + " WHERE detailID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, cart.getBookID());
            stm.setInt(2, cart.getQuantity());
            stm.setFloat(3, cart.getPrice());
            stm.setInt(4, cart.getOrderID());
            stm.setInt(5, cart.getId());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public void CreateOrder(OrderDTO order) {
        try {
            con = DBCon.getConnection();
            String sql = "insert into tblOrders (orderID, userID, deliverAddr, paymentCheck, status)"
                    + " values(?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setInt(1, order.getId());
            stm.setString(2, order.getUserID());
            stm.setString(3, order.getDevAddr());
            stm.setString(4, order.getPaymentCheck());
            stm.setInt(5, order.getStatus());
            rs = stm.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public void updateOrder(OrderDTO order) {
//        try {
//            con = DBCon.getConnection();
//            String sql = "";
//            stm = con.prepareStatement(sql);
//            stm.setInt(1, order.getId());
//            stm.setString(2, order.getUserID());
//            stm.setString(3, order.getBookList());
//            rs = stm.executeQuery();
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stm != null) {
//                    stm.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (Exception e) {
//            }
//        }
    }

    public OrderDTO getOrder() {
        OrderDTO order = null;
        try {
            con = DBCon.getConnection();
            String sql = "select userID from tblOrderDetail";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return order;
    }

    public int findUserOrder(String id) {
        int uid = 0;
        try {
            con = DBCon.getConnection();
            String sql = "select userID from tblOrders";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if(rs.getString("userID").equalsIgnoreCase(id))
                    uid = rs.getInt("orderID");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return uid;
    }

    public int IDGen(String tb) {
        int lastest = 1;
        try {
            String sql;
            con = DBCon.getConnection();
            if (tb.equalsIgnoreCase("cart")) {
                sql = "select count(detailID) as total from tblOrderDetail";
            } else {
                sql = "select count(orderID) as total from tblOrders";
            }
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                lastest += rs.getInt("total");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return lastest;
    }

    public static void main(String[] args) {
        bookDAO dao = new bookDAO();
        List<CategoryDTO> list = dao.listCate();
        System.out.println(list);
    }

    public List<bookDTO> SearchByName(String Name) {
        List<bookDTO> list = new ArrayList<>();
        try {
            con = DBCon.getConnection();
            String sql = "Select productID, bookName, price, quantity, categor=(select cateName from tblCategory where categoryID = tblProduct.categoryID), Img,descr,Published,Writer,Cover,Penciler,active from tblProduct where bookName like ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + Name + "%");
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new bookDTO(
                            rs.getString("productID"),
                            rs.getString("bookName"),
                            rs.getFloat("price"),
                            rs.getInt("quantity"),
                            rs.getString("categor"),
                            rs.getString("Img"),
                            rs.getString("descr"),
                            rs.getString("Cover"),
                            rs.getString("Writer"),
                            rs.getString("Penciler"),
                            rs.getString("Published"),
                            rs.getBoolean("active")));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public int UpdateBook(bookDTO o) {
        bookDAO dao = new bookDAO();
        List<CategoryDTO> cate = dao.listCate();
        int done = 0;
        try {
            con = DBCon.getConnection();
            String sql = "UPDATE tblProduct"
                    + " SET bookName= ?,price = ?,quantity = ?,categoryID = ?,Img = ?,descr = ?,Published = ?,Writer = ?,Cover = ?,Penciler = ?,active = ?"
                    + " WHERE productID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, o.getName());
            stm.setFloat(2, o.getPrice());
            stm.setInt(3, o.getQuantity());
            for (CategoryDTO c : cate) {
                if (c.getName().equalsIgnoreCase(o.getCateID())) {
                    stm.setString(4, c.getId());
                }
            }
            if (o.isStatus() == true) {
                stm.setString(11, "1");
            } else {
                stm.setString(11, "0");
            }
            stm.setString(5, o.getImg());
            stm.setString(6, o.getDercs());
            stm.setString(7, o.getPub());
            stm.setString(8, o.getWrite());
            stm.setString(9, o.getCover());
            stm.setString(10, o.getPen());
            stm.setString(12, o.getId());
            done = stm.executeUpdate();
            System.out.println(done);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return done;
    }

    public bookDTO getBookbyID(String id) {
        bookDAO dao = new bookDAO();
        List<bookDTO> list = dao.listBook();
        bookDTO book = null;
        for (bookDTO o : list) {
            if (o.getId().equalsIgnoreCase(id)) {
                book = o;
            }
        }
        return book;
    }

    public int addBook(bookDTO o) {
        int done = 0;
        bookDAO dao = new bookDAO();
        List<CategoryDTO> cate = dao.listCate();
        try {
            con = DBCon.getConnection();
            String sql = "INSERT INTO tblProduct(productID,bookName,price,quantity,categoryID,Img,descr,Published,Writer,Cover,Penciler,active)\n"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, o.getId());
            stm.setString(2, o.getName());
            stm.setFloat(3, o.getPrice());
            stm.setInt(4, o.getQuantity());
            for (CategoryDTO c : cate) {
                if (c.getName().equalsIgnoreCase(o.getCateID())) {
                    stm.setString(5, c.getId());
                }
            }
            stm.setString(6, o.getImg());
            stm.setString(7, o.getDercs());
            stm.setString(8, o.getPub());
            stm.setString(9, o.getWrite());
            stm.setString(10, o.getCover());
            stm.setString(11, o.getPen());
            if (o.isStatus() == true) {
                stm.setString(12, "1");
            } else {
                stm.setString(12, "0");
            }
            done = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return done;
    }

    public List<bookDTO> listBook() {
        List<bookDTO> list = new ArrayList<>();
        try {
            con = DBCon.getConnection();
            String sql = "Select productID, bookName, price, quantity, categor=(select cateName from tblCategory where categoryID = tblProduct.categoryID), Img,descr,Published,Writer,Cover,Penciler,active from tblProduct";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new bookDTO(
                            rs.getString("productID"),
                            rs.getString("bookName"),
                            rs.getFloat("price"),
                            rs.getInt("quantity"),
                            rs.getString("categor"),
                            rs.getString("Img"),
                            rs.getString("descr"),
                            rs.getString("Cover"),
                            rs.getString("Writer"),
                            rs.getString("Penciler"),
                            rs.getString("Published"),
                            rs.getBoolean("active")));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List<bookDTO> listBookByCate(String cateID) {
        List<bookDTO> list = new ArrayList<>();
        try {
            con = DBCon.getConnection();
            String sql = "Select productID, bookName, price, quantity, categor=(select cateName from tblCategory where categoryID = tblProduct.categoryID),Img,descr,Published,Writer,Cover,Penciler,active from tblProduct where categoryID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, cateID);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new bookDTO(
                            rs.getString("productID"),
                            rs.getString("bookName"),
                            rs.getFloat("price"),
                            rs.getInt("quantity"),
                            rs.getString("categor"),
                            rs.getString("Img"),
                            rs.getString("descr"),
                            rs.getString("Cover"),
                            rs.getString("Writer"),
                            rs.getString("Penciler"),
                            rs.getString("Published"),
                            rs.getBoolean("active")));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List<CategoryDTO> listCate() {
        List<CategoryDTO> list = new ArrayList<>();
        try {
            con = DBCon.getConnection();
            String sql = "Select categoryID, cateName from tblCategory";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getString("categoryID"), rs.getString("cateName")));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

}
