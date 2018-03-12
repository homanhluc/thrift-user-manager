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
import vng.luchm.domain.Statistics;
import vng.luchm.main.ThriftUserManagerTesting;
import vng.luchm.testing.TestGetAllUsers;

/**
 *
 * @author luchm
 */
public class LoadTest extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        TestGetAllUsers testGetAllUsers = new TestGetAllUsers();
        testGetAllUsers.sendingGetAllUsers1000();

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = resp.getWriter();
        out.println("<html>"); //html
        out.println("<head>"); //head

        
        out.println("<style> table, th, td { border: 1px solid black; border-collapse: collapse; } </style>");

        out.println("</head>"); //end-head

        out.println("<body>"); //body

        out.println("<h1>Statistics</h1>");
        out.println("<h4>Min (ms): " + testGetAllUsers.min+ "</h4>");
        out.println("<h4>Max (ms): " + testGetAllUsers.max + "</h4>");
        out.println("<h4>Through put: " + testGetAllUsers.through + "/sec </h4>");
        out.println("<table style=\"width:100%\">");
        out.println("<tr>");
        out.println("<th>Load Time</th>");
        out.println("<th>Body size in bytes</th> ");
        out.println("<th>Response Code</th>");
        out.println("<th>Response Messsage</th>");
        out.println("</tr>");

        for (Statistics s : TestGetAllUsers.listStatisticses) {
            out.println("<tr>");
            out.println("<td>" + s.getLoadTime() + "</td>");
            out.println("<td>" + s.getSizeInByte() + "</td>");
            out.println("<td>" + s.getResponseCode() + "</td>");
            out.println("<td>" + s.getResponseMessage()+ "</td>");
            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</body>"); //end-body

        out.println("</html>");//end-html
    }

}
