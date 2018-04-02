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
public class TestGetUserById {
    private static UserRepositoryMySQLImp handler = new UserRepositoryMySQLImp();
    public static void main(String[] args) throws TException {
        System.out.println("----- Test UserById -----");
        new FormTest("getUserById(1).Id", "1", handler.getUserById("1").Id);
    }
}
