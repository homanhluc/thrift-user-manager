/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.controller;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.luchm.handler.Handler;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class GetUserById extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Handler handler = new Handler();
            User o = handler.getUserById(req.getParameter("id"));
            String json = new Gson().toJson(o);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } catch (TException ex) {
            logger.error("getUserById() - " + ex.getMessage());
        }
    }
}
