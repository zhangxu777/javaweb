package com.xxxx.oa.web.controller;

import com.xxxx.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        String proid = request.getParameter("proid");
        String json = "{}";

        try {
            Province province = new Province();
            conn = DBUtil.getConnection();
            String sql = "select id, name, jiancheng, shenghui from province where id = ?";
            ps.setString(1, proid);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                province.setId(rs.getInt("id"));
                province.setName(rs.getString("name"));
                province.setJiancheng(rs.getString("jiancheng"));
                province.setShenghui(rs.getString("shenghui"));
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.close(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.setContentType("ext");

    }
}
