/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.handler;

import java.io.Serializable;
import java.util.List;
import org.apache.thrift.TException;
import vng.luchm.config.ThriftClient;
import vng.luchm.thrift.Operation;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class Handler implements Serializable {

    private static final long serialVersionUID = 1L;

    public static List<User> getAllUser() throws TException {
        return ThriftClient.client.getAllUsers();
    }

    public static User getUserById(String id) throws TException {
        return ThriftClient.client.getUserById(id);
    }

    public static void login(String userName, String passWord) throws TException {
        ThriftClient.client.userlogin(userName, passWord);
    }

    public static void register(User u) throws TException {
        ThriftClient.client.userRegister(u);
    }

    public static void setScore(Operation op, String id) throws TException {
        ThriftClient.client.setScore(op, id);
    }
}
