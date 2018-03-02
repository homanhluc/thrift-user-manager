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

/**
 *
 * @author luchm
 */
public class Login extends HttpServlet implements Serializable {

    private static final Logger logger = Logger.getLogger(Login.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        post(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        get(req, resp);
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Object o = ThriftClient.client.getUserById(req.getParameter("id"));
            String json = new Gson().toJson(o);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } catch (TException ex) {
            logger.error("getUserById() - " + ex.getMessage());
        }
    }

    private void post(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            String username = req.getHeader("username");
            String password = req.getHeader("password");
            ThriftClient.client.userlogin(username, password);

        } catch (TException ex) {
            logger.error("userLogin() - " + ex.getMessage());
        }
    }
}
