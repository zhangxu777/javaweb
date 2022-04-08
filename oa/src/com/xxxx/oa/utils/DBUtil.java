package com.xxxx.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;


public class DBUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");


    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void close(Connection conn, Statement ps, ResultSet rs) throws SQLException {

        if (conn != null) {
            conn.close();
        }

        if (rs != null) {
            rs.close();
        }

        if (ps != null) {
            ps.close();
        }

    }
}
