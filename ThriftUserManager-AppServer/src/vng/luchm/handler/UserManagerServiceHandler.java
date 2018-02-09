/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.handler;

import java.util.List;
import org.apache.thrift.TException;
import vng.luchm.repository.IUserRepository;
import vng.luchm.repository.UserRepositoryMySQLImp;
import vng.luchm.thrift.LoginFailed;
import vng.luchm.thrift.Operation;
import vng.luchm.thrift.RegisterFailed;
import vng.luchm.thrift.User;
import vng.luchm.thrift.UserManagerService;
import vng.luchm.thrift.UserNotFound;

/**
 *
 * @author luchm
 */
public class UserManagerServiceHandler implements UserManagerService.Iface {
    final UserRepositoryMySQLImp uri = new UserRepositoryMySQLImp();
    @Override
    public void setScore(Operation op) throws TException {
        switch (op) {
            case INCREASE:
                uri.increase();
            case DECREASE:
                uri.decrease();
            default:
                throw new TException("Unknown operation " + op);
        }
    }

    @Override
    public void userRegister(User userInfo) throws RegisterFailed, TException {
        uri.userRegister(userInfo);
    }

    @Override
    public void userlogin(String userName, String passWord) throws LoginFailed, TException {
        uri.userlogin(userName, passWord);
    }

    @Override
    public List<User> getAllUsers() throws TException {
        return uri.getAllUsers();
    }

    @Override
    public User getUserById(String id) throws UserNotFound, TException {
        return uri.getUserById(id);
    }
    
}
