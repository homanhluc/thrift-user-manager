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
import vng.luchm.main.WebServerMain;
import vng.luchm.thrift.Operation;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class Handler implements Serializable {

    private static final long serialVersionUID = 1L;

    public synchronized static List<User> getAllUser() throws TException {
        return ThriftClient.client.getAllUsers();
    }

    public synchronized static User getUserById(String id) throws TException {
        if (WebServerMain.cache.get(Integer.parseInt(id)) != null) {
            return (User) WebServerMain.cache.get(Integer.parseInt(id));
        } else {
            User user = ThriftClient.client.getUserById(id);
            if (user.getId() != null) {
                WebServerMain.cache.set(Integer.parseInt(id), user);
                return user;
            }
            return null;
        }
    }

    public synchronized static void login(String userName, String passWord) throws TException {
        ThriftClient.client.userlogin(userName, passWord);
    }

    public synchronized static void register(User u) throws TException {
        ThriftClient.client.userRegister(u);
    }

    public synchronized static void setScore(Operation op, String id) throws TException {
        ThriftClient.client.setScore(op, id);
    }
}
