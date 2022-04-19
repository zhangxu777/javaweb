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
import java.sql.SQLException;

public class DeptSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

      /*  out.print("!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>新增部门</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1>新增部门</h1>");
        out.print("		<hr >");
        out.print("		<form action='/oa/dept/save' method='post'>");
        out.print("                部门编号<input type='text' name='deptno'/><br>");
        out.print("                部门名称<input type='text' name='dname'/><br>");
        out.print("                部门位置<input type='text' name='loc'/><br>");
        out.print("			<input type='submit' value='保存'/><br>");
        out.print("		</form>");
        out.print("	</body>");
        out.print("</html>");*/

        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            String sql = "insert into dept(deptno, dname, loc) value(?, ?, ?) ";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            i = ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.close(conn, ps, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (i == 1) {
            request.getRequestDispatcher("/dept/list").forward(request, response);
        }





    }
}
