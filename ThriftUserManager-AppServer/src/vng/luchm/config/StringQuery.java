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
//        String query = "INSERT INTO "
//                    + TABLE
//                    +" (UserName, PassWord, Score, CreatedDate, UpdatedDate) VALUES ("
//                    + "'" + u.getUserName() + "'" + ","
//                    + "'" + u.getPassWord() + "'" + ","
//                    + "'" + u.getScore() + "'" + ","
//                    + "'" + u.getCreatedDate() + "'" + ","
//                    + "'" + u.getUpdatedDate() + "'" + ")";
        String query = "INSERT INTO "
                + TABLE
                + " (UserName, PassWord, Score, CreatedDate, UpdatedDate) SELECT "
                + "'" + u.getUserName() + "'" + ","
                + "'" + u.getPassWord() + "'" + ","
                + "'" + u.getScore() + "'" + ","
                + "'" + u.getCreatedDate() + "'" + ","
                + "'" + u.getUpdatedDate() + "'"
                + " FROM usermanager.user "
                + "WHERE NOT EXISTS(SELECT UserName FROM usermanager.user WHERE UserName = '"+ u.getUserName() +"')"
                + " LIMIT 1";
        return query;
    }
    public static String checkLogin(String username, String password) {
        String query = "SELECT Id FROM "
                + TABLE
                + " WHERE UserName = " + "'" + username + "'"
                + " && PassWord = " + "'" + password + "'";
        return query;
    }
    public static String getUser(String id) {
        String query = "SELECT * FROM "
                + TABLE
                + " WHERE Id = " + "'" + id + "'";
        return query;
    }
    public static String increase(String id) {
        String query = "UPDATE usermanager.user SET Score = Score + 1 WHERE Id = " + id;
        return query;
    }
    public static String decrease(String id) {
        String query = "UPDATE usermanager.user SET Score = Score - 1 WHERE Id = " + id;
        return query;
    }
    public static String getAll() {
        String query = "SELECT * FROM "
                + TABLE ;
        return query;
    }
}
