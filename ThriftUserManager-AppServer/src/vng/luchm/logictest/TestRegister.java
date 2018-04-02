/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.logictest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import vng.luchm.repository.UserRepositoryMySQLImp;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class TestRegister {

    private static UserRepositoryMySQLImp handler = new UserRepositoryMySQLImp();

    public static void main(String[] args) {
        System.out.println("----- Test Register -----");
        test1();
        test2();
        test3();
    }

    private static void test1() {
        User u = new User();
        u.setUserName("luc");
        u.setPassWord("123");
        if (handler.userRegister(u) == false) {
            System.out.println("User register luc/123 (exist) : true (register failed)");
        } else {
            System.out.println("User register luc/123 (exist) : false");
        }
    }

    private static void test2() {
        User u = new User();
        u.setUserName(null);
        u.setPassWord(null);
        if (handler.userRegister(u) == false) {
            System.out.println("User register null/null (non-exist) : true (register failed)");
        } else {
            System.out.println("User register null/null (non-exist) : false");
        }
    }

    private static void test3() {
        User u = new User();
        u.setUserName("linh");
        u.setPassWord(null);
        if (handler.userRegister(u) == false) {
            System.out.println("User register linh/null (non-exist) : true (register failed)");
        } else {
            System.out.println("User register linh/null (non-exist) : false");
        }
    }

    private static void test4() {
        User u = new User();
        u.setUserName("luc@");
        u.setPassWord("123");
        if (handler.userRegister(u) == false) {
            System.out.println("User register luc@/123 (non-exist) : true (register failed)");
        } else {
            System.out.println("User register luc@/123 (non-exist) : false");
        }
    }
}
