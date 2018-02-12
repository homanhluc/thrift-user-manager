/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.thrift.TException;
import vng.luchm.config.ThriftClient;
import vng.luchm.thrift.Operation;

/**
 *
 * @author luchm
 */
public class SetScore extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String setScore = req.getParameter("set");
            String id = req.getParameter("id");
            
            if (setScore.equals("in")) {
                ThriftClient.client.setScore(Operation.INCREASE, id);
            }
            if (setScore.equals("de")) {
                ThriftClient.client.setScore(Operation.DECREASE, id);
            }
            
            Object o = ThriftClient.client.getUserById(id);
            
            String json = new Gson().toJson(o);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
            
        } catch (TException ex) {
            System.out.println("LOioz");
            Logger.getLogger(SetScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
