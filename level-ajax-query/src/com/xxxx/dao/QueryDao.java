package com.xxxx.dao;

import com.xxxx.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;
    private String url = "jdbc:mysql://10.10.5.202:3306/mybatis";
    private String user = "root";
    private String password = "123456";


    // Query Province
    public List<Province> queryProvinceList() throws ClassNotFoundException, SQLException {
        List<Province> provinces = new ArrayList<>();
        try {
            Province p = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            sql = "select id, name, jiancheng, shenghui from province order by id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                p = new Province();
                p.setId(rs.getInt("id"));
                p.setJiancheng(rs.getString("jiancheng"));
                p.setName(rs.getString("name"));
                p.setShenghui(rs.getString("shenghui"));
                provinces.add(p);
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if( rs != null){
                rs.close();
            }
            if( ps != null){
                ps.close();
            }
            if( conn != null){
                conn.close();
            }
        }

        return provinces;


    }
}
