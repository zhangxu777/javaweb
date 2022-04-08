package com.xxxx.oa.web.action;

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

public class DeptDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String deptno = request.getParameter("deptno");
        System.out.println(deptno);


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("	<head>");
        out.println("		<meta charset='utf-8'>");
        out.println("		<title>部门详情</title>");
        out.println("	</head>");
        out.println("	<body>");
        out.println("		<h1>部门详情</h1>");
        out.println("		<hr >");



        try {
            conn = DBUtil.getConnection();
            String sql = "select dname, loc from dept where deptno = ?";

             ps = conn.prepareStatement(sql);
             ps.setString(1, deptno);
             rs = ps.executeQuery();
            while (rs.next()){
                //String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.println("                部门编号："+deptno+" <br>");
                out.println("                部门名称："+dname+"<br>");
                out.println("        部门位置："+loc+"<br>");
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

        out.println("		<input type='button' value='后退' onclick='window.history.back()'/>");
        out.println("	</body>");
        out.println("</html>");

    }
}
