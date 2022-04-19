package com.xxxx.oa.web.action;

import com.xxxx.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String detpno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from dept where deptno = ?";
        int i = 0;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, detpno);
            i = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (i == 1){
            System.out.println(i);
            request.getRequestDispatcher("/dept/list").forward(request, response);
        }



    }
}
