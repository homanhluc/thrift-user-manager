/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.controller;

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
            handler.login(username, password);

        } catch (TException ex) {
            logger.error("userLogin() - " + ex.getMessage());
        }
    }
}
