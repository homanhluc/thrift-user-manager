/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vng.luchm.testing.TestGetAllUsers;

/**
 *
 * @author luchm
 */
public class Benchmark extends HttpServlet {
    private int totalReq = 0; 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         TestGetAllUsers s = new TestGetAllUsers();
        s.sendingGetAllUsers();
        //////////
        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        
        PrintWriter out = resp.getWriter();
        out.println("<html>"); //html
        out.println("<head>"); //head
        out.println("<meta http-equiv=\"refresh\" content=\"1\" />");
        out.println("<style> table, th, td { border: 1px solid black; border-collapse: collapse; } </style>");
        
        out.println("</head>"); //end-head
        
        out.println("<body>"); //body
        
        out.println("<h1>Statistics</h1>");
        out.println("<table style=\"width:100%\">");
        out.println("<tr>");
        out.println("<th>TotalReq</th>");
        out.println("<th>LoadTime</th>");
        out.println("<th>SizeInByte</th> ");
        out.println("<th>ResponseCode</th>");
        out.println("<th>ResponseMessage</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>"+ totalReq++ +"</td>");
        out.println("<td>"+ TestGetAllUsers.s.getLoadTime() +"</td>");
        out.println("<td>" + TestGetAllUsers.s.getSizeInByte() +"</td>");
        out.println("<td>" + TestGetAllUsers.s.getResponseCode() +"</td>");
        out.println("<td>" + TestGetAllUsers.s.getResponseMessage()+"</td>");
        out.println("</tr>");
        out.println("</table>");
        
        out.println("</body>"); //end-body
        
        out.println("</html>");//end-html
    }

}
