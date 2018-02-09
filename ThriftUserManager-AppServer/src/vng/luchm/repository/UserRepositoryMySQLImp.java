/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.repository;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import vng.luchm.config.ConnectionPool;
import vng.luchm.config.StringQuery;
import vng.luchm.config.SystemInfo;
import vng.luchm.pool.DataSource;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class UserRepositoryMySQLImp implements IUserRepository {

    @Override
    public void userRegister(User u) {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            int addData = statement.executeUpdate(StringQuery.insert(u));
            if(addData < 1) {
                System.out.println("khong them duoc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            DataSource.closeConnection(resultset, statement, connect);
        }
    }

    @Override
    public void increase() {

    }

    @Override
    public void decrease() {

    }

    @Override
    public void userlogin(String userName, String passWord) {
    }

    @Override
    public User getUserById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
