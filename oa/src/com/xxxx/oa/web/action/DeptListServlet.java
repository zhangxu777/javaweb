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

public class DeptListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("        <!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>部门列表页面</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1 align='center'>部门列表</h1>");
        out.print("		<hr >");
        out.print("		<table border='1px' align='center' width='50%'>");
        out.print("			<tr>");
        out.print("				<th>序号</th>");
        out.print("				<th>部门编号</th>");
        out.print("				<th>部门名称</th>");
        out.print("				<th>操作</th>");
        out.print("			</tr>");


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno, dname, loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.print("			<tr>");
                out.print("				<td>"+(++i)+"</td>");
                out.print("				<td>"+deptno+"</td>");
                out.print("				<td>"+dname+"</td>");
                out.print("				<td>");
                out.print("					<a href=''>删除</a>");
                out.print("					<a href='edit.html'>修改</a>");
                out.print("					<a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.print("				</td>");
                out.print("			</tr>");




            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtil.close(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.print("		</table>");
        out.print("		<hr >");
        out.print("		<a href='add.html'>新增部门</a>");
        out.print("	</body>");
        out.print("</html>");
    }
}

