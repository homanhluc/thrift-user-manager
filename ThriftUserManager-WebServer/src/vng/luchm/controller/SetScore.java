/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.luchm.config.ThriftClient;
import vng.luchm.thrift.Operation;

/**
 *
 * @author luchm
 */
public class SetScore extends HttpServlet implements Serializable {

    private static final Logger logger = Logger.getLogger(SetScore.class);
    private static final long serialVersionUID = 1L;

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
            logger.error("setCore() - " + ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String json = new Gson().toJson(ThriftClient.client.getAllUsers());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);

        } catch (TException ex) {
            System.out.println("getAllUsers error !!!");
            logger.error("getAllUsers() - " + ex.getMessage());
        }
    }

}
