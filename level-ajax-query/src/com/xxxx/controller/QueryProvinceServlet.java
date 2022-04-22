package com.xxxx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.dao.QueryDao;
import com.xxxx.entity.Province;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = "{}";
        try {
            QueryDao dao = new QueryDao();
            List<Province> provinces = dao.queryProvinceList();
            for (Province province : provinces) {
                System.out.println(province);

            }
            if (provinces != null) {
                ObjectMapper om = new ObjectMapper();
                json = om.writeValueAsString(provinces);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
