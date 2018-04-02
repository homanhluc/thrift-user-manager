/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.logictest;

import org.apache.thrift.TException;
import vng.luchm.repository.UserRepositoryMySQLImp;

/**
 *
 * @author luchm
 */
public class TestLogin {

    private static UserRepositoryMySQLImp handler = new UserRepositoryMySQLImp();

    public static void main(String[] args) throws TException {
        System.out.println("----- Test Login -----");
        new FormTest("luc/123", "true", String.valueOf(handler.userlogin("luc", "123")));
        new FormTest("luc/1234", "false", String.valueOf(handler.userlogin("luc", "1234")));
    }
}
