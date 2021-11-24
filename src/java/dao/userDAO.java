package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDAO {
    public userDTO Login(String id, String pass){
        String role = "";
        String name = "";
        String addr = "";
        int phone;
        userDTO DTO = null;
        Connection con = null;
        PreparedStatement stm=null;
        ResultSet rs =null;
        try {
            con = DBCon.getConnection();
            if(con!=null){
                String sql = "Select userID, userName, roleID, phone, addr from tblUsers where userID=? and pass=?";
            stm=con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, pass);
            rs = stm.executeQuery();
                if(rs.next()) {
                    name=rs.getString("userName");
                    role=rs.getString("roleID");
                    addr=rs.getString("addr");
                    phone=rs.getInt("phone");
                    DTO = new userDTO(id, pass, name, role, phone, addr);
                    
                }
            }
        } catch (Exception e) {
        }finally{
            try {
                if (rs!=null) rs.close();
                if (stm!=null) stm.close();
                if (con!=null) con.close();
            } catch (Exception e) {
            }
        }
        return DTO;
    }
    
    public List<userDTO> list (String search){
        List<userDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm=null;
        ResultSet rs =null;
        try {
            conn=DBCon.getConnection();
            if(conn!=null){
                String sql = "Select userID, userName, roleID, phone, addr from tblUsers where fullName like '%"+search+"%'";
                stm = conn.prepareStatement(sql);
                //stm.setString(1, search);
                rs=stm.executeQuery();
                while(rs.next()){                    
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("userName");
                    String roleID = rs.getString("roleID");
                    String addr=rs.getString("addr");
                    int phone=rs.getInt("phone");
                    String password = "";
                    list.add(new userDTO(userID,password,fullName,roleID,phone,addr));
                }
            }
        } catch (Exception e) {
        }finally{
            try {
                if (rs!=null) rs.close();
                if (stm!=null) stm.close();
                if (conn!=null) conn.close();
            } catch (Exception e) {
            }
        }return list;
    }
    public boolean updateUser(userDTO user) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        String a = user.getName();
        String b = user.getRoleID();
        String c = user.getId();
        try {
            conn = DBCon.getConnection();
            if(conn!= null){
                String sql = "Update tblUsers Set userName=?, roleID=? where userID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, user.getName());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getId());
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
        }finally{
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }return check;
    }
    
    public boolean deleteUser(String ID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBCon.getConnection();
            if(conn!= null){
                String sql = "delete tblUsers where userID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, ID);
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
        }finally{
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }return check;
    }
    
    public boolean checkDup(String userID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBCon.getConnection();
            if(conn!= null){
                String sql = "select userID from tblUsers where userID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if(rs.next()) check = true;
            }
        } catch (Exception e) {
        }finally{
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
    
    public boolean insertUser(userDTO user) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBCon.getConnection();
            if(conn!= null){
                String sql = "insert into tblUsers(userID,userName,pass,roleID,phone,addr) values(?,?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, user.getId());
                stm.setString(2, user.getName());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getRoleID());
                stm.setString(5, user.getAddr());
                stm.setInt(6, user.getPhone());
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
        }finally{
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
}
