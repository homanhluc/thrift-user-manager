/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.logictest;

import vng.luchm.repository.UserRepositoryMySQLImp;

/**
 *
 * @author luchm
 */
public class TestScore {
    private static UserRepositoryMySQLImp handler = new UserRepositoryMySQLImp();
    public static void main(String[] args) {
        System.out.println("----- Test Score -----");
        
        handler.increase("1");
        new FormTest("increase score id=1", String.valueOf(handler.getUserById("1").Score), String.valueOf(handler.getUserById("1").Score));
        
        handler.decrease("1");
        new FormTest("decrease score id=1", String.valueOf(handler.getUserById("1").Score), String.valueOf(handler.getUserById("1").Score));
    }
}
