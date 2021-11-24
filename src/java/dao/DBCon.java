package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCon {
    public static Connection getConnection() throws ClassNotFoundException{
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = null;
        try {
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=BookRental;";
            String us = "sa", pass = "123";
            conn = DriverManager.getConnection(dbURL, us, pass);
            if (conn != null)
                return conn;
        } catch (SQLException e) { System.out.println(e);
        }return null;
    }
}
