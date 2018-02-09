/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.config;

import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class StringQuery {
    private static final String TABLE = "user"; 
    public static String insert(User u) {
        String query = "INSERT INTO "
                    + TABLE
                    +" (UserName, PassWord, Score, CreatedDate, UpdatedDate) VALUES ("
                    + "'" + u.getUserName() + "'" + ","
                    + "'" + u.getPassWord() + "'" + ","
                    + "'" + u.getScore() + "'" + ","
                    + "'" + u.getCreatedDate() + "'" + ","
                    + "'" + u.getUpdatedDate() + "'" + ")";
        return query;
    }
}
