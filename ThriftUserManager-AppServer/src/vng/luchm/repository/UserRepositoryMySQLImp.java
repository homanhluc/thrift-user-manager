/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import vng.luchm.config.StringQuery;
import vng.luchm.log.LogConfig;
import vng.luchm.pool.DataSource;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class UserRepositoryMySQLImp implements IUserRepository {

    private Connection connect;
    private Statement statement;
    private ResultSet resultset;
    private final String _SUCCESS = "SUCCESS";
    private final String _ERROR = "ERROR";
    //private static final Logger logger = Logger.getLogger(UserRepositoryMySQLImp.class);

    public UserRepositoryMySQLImp() {
        this.resultset = null;
    }

    @Override
    public void userRegister(User u) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.insert(u));
            
            LogConfig.saveLog(_SUCCESS, UserRepositoryMySQLImp.class.toString(), StringQuery.insert(u));
            //logger.info(StringQuery.insert(u));
        } catch (SQLException | ClassNotFoundException ex) {
            //logger.error(StringQuery.insert(u) + " - " + ex.getMessage());
            LogConfig.saveLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.insert(u));
        } finally {
            DataSource.returnConnection(connect);
        }
    }

    @Override
    public void userlogin(String userName, String passWord) {
        try {
            User u = new User();
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            resultset = statement.executeQuery(StringQuery.checkLogin(userName, passWord));
            while (resultset.next()) {
                u.setId(resultset.getString("Id"));
            }
            getUserById(u.getId());
        } catch (SQLException | ClassNotFoundException ex) {
            LogConfig.saveLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.checkLogin(userName, passWord));
        } finally {
            DataSource.returnConnection(connect);
        }
    }

    @Override
    public User getUserById(String id) {

        User u = new User();
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            resultset = statement.executeQuery(StringQuery.getUser(id));
            while (resultset.next()) {
                u.setId(resultset.getString("Id"));
                u.setUserName(resultset.getString("UserName"));
                u.setPassWord(resultset.getString("PassWord"));
                u.setScore(resultset.getInt("Score"));
                u.setCreatedDate(resultset.getString("CreatedDate"));
                u.setUpdatedDate(resultset.getString("updatedDate"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LogConfig.saveLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.getUser(id));
        } finally {
            DataSource.returnConnection(connect);
        }
        return u;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> list = new LinkedList<>();
        
        try {
            connect = DataSource.getConnection();
            PreparedStatement statement = connect.prepareStatement(StringQuery.getAll());
            resultset = statement.executeQuery();
            while (resultset.next()) {
                User u = new User();
                u.setId(resultset.getString("Id"));
                u.setUserName(resultset.getString("UserName"));
                u.setPassWord(resultset.getString("PassWord"));
                u.setScore(resultset.getInt("Score"));
                u.setCreatedDate(resultset.getString("CreatedDate"));
                u.setUpdatedDate(resultset.getString("UpdatedDate"));
                list.add(u);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LogConfig.saveLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.getAll());
        } finally {
            DataSource.returnConnection(connect);
        }

        return list;
    }

    @Override
    public void increase(String id) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.increase(id));
            LogConfig.saveLog(_SUCCESS, UserRepositoryMySQLImp.class.toString(), StringQuery.increase(id));
        } catch (SQLException | ClassNotFoundException ex) {
            LogConfig.saveLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.increase(id));
        } finally {
            DataSource.returnConnection(connect);
        }
    }

    @Override
    public void decrease(String id) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.decrease(id));
            LogConfig.saveLog(_SUCCESS, UserRepositoryMySQLImp.class.toString(), StringQuery.decrease(id));
        } catch (SQLException | ClassNotFoundException ex) {
            LogConfig.saveLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.decrease(id));
        } finally {
            DataSource.returnConnection(connect);
        }
    }

    @Override
    public void logQuery(String query) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        } finally {
            DataSource.returnConnection(connect);
        }
    }

}
