/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.handler;

import java.util.List;
import org.apache.thrift.TException;
import vng.luchm.repository.UserRepositoryMySQLImp;
import vng.luchm.thrift.Operation;
import vng.luchm.thrift.User;
import vng.luchm.thrift.UserManagerService;

/**
 *
 * @author luchm
 */
public class UserManagerServiceHandler implements UserManagerService.Iface {
    final UserRepositoryMySQLImp uri = new UserRepositoryMySQLImp();
    @Override
    public void setScore(Operation op, String id) throws TException {
        switch (op) {
            case INCREASE:
                uri.increase(id);
                break;
            case DECREASE:
                uri.decrease(id);
                break;
            default:
                throw new TException("Unknown operation " + op);
        }
    }

    @Override
    public boolean userRegister(User userInfo) throws TException {
        return uri.userRegister(userInfo);
    }

    @Override
    public boolean userlogin(String userName, String passWord) throws TException {
        return uri.userlogin(userName, passWord);
    }

    @Override
    public List<User> getAllUsers() throws TException {
        return uri.getAllUsers();
    }

    @Override
    public User getUserById(String id) throws TException {
        return uri.getUserById(id);
    }
    
}
