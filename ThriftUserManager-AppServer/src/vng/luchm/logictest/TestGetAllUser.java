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
public class TestGetAllUser {

    private static UserRepositoryMySQLImp handler = new UserRepositoryMySQLImp();

    public static void main(String[] args) throws TException {
        System.out.println("----- Test GetAllUsers -----");
        new FormTest("getAllUsers().Size()", "13", String.valueOf(handler.getAllUsers().size()));
        new FormTest("getAllUsers().get(0).UserName", "luc", handler.getAllUsers().get(0).UserName);
    }
}
