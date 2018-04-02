/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Register extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Register.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new User();
        resp.setContentType("application/json");
        u.setUserName(req.getHeader("username"));
        u.setPassWord(req.getHeader("password"));
        u.setScore(Integer.parseInt(req.getHeader("score")));
        u.setCreatedDate(createdDay());
        u.setUpdatedDate(createdDay());

        try {
            Handler handler = new Handler();
            Status s = new Status();
            if (handler.register(u) == true) {
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
            logger.error("userRegister() - " + ex.getMessage());
        }
    }

    private String createdDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    class Status {
        boolean status;
    }
}
