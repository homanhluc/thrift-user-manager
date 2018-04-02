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

/**
 *
 * @author luchm
 */
public class Login extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Login.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Handler handler = new Handler();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            String username = req.getHeader("username");
            String password = req.getHeader("password");
            Status s = new Status();
            if(handler.login(username, password) == true) {
                s.status = true;
                String json = new Gson().toJson(s);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
            } else {
                s.status = false;
                String json = new Gson().toJson(s);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
            }

        } catch (TException ex) {
            logger.error("userLogin() - " + ex.getMessage());
        }
    }
    class Status {
        boolean status;
    }
}
