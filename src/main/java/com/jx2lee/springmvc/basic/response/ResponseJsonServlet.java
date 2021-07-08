package com.jx2lee.springmvc.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jx2lee.springmvc.basic.HelloData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("jx2lee");
        helloData.setAge(31);

        //{"username": "jx2lee", "age": 31}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
