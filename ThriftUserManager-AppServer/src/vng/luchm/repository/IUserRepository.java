/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.repository;

import java.util.List;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public interface IUserRepository {
    void increase(String id);
    void decrease(String id);
    void userRegister(User userInfo);
    void userlogin(String userName, String passWord);
    User getUserById(String id);
    List<User> getAllUsers();
}
