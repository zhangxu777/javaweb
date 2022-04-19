package com.xxxx.oa.web.controller;

import com.xxxx.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String proid = request.getParameter("proid");
        String name = "";
        
        

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DBUtil.getConnection();
            String sql = "select name from city where provinceid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, proid);
            rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
            }
            System.out.println(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.close(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println(name);
        out.flush();
        out.close();







    }
}
