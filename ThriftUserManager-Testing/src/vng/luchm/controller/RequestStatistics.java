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
import vng.luchm.main.ThriftUserManagerTesting;
import vng.luchm.testing.TestGetAllUsers;

/**
 *
 * @author luchm
 */
public class RequestStatistics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
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
        out.println("<th>Method</th>");
        out.println("<th>TotalReq</th>");
        out.println("<th>Min</th> ");
        out.println("<th>Max</th>");
        out.println("<th>Throughput (?req/sec)</th>");
        out.println("<th>Load time (?sec/req)</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>"+ TestGetAllUsers.s.getMethodName() +"</td>");
        out.println("<td>"+ TestGetAllUsers.s.getTotalReq() +"</td>");
        out.println("<td>" + TestGetAllUsers.s.getMin() +"</td>");
        out.println("<td>" + TestGetAllUsers.s.getMax() +"</td>");
        out.println("</tr>");
        out.println("</table>");
        
        out.println("</body>"); //end-body
        
        out.println("</html>");//end-html
    }

}
