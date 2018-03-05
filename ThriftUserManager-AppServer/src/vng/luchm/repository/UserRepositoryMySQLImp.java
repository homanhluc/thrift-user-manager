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
    private static final Logger logger = Logger.getLogger(UserRepositoryMySQLImp.class);

    public UserRepositoryMySQLImp() {
        this.resultset = null;
    }

    @Override
    public void userRegister(User u) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.insert(u));
            logger.info(StringQuery.insert(u));
        } catch (SQLException | ClassNotFoundException ex) {
            logger.error(StringQuery.insert(u) + " - " + ex.getMessage());
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
            logger.error(StringQuery.checkLogin(userName, passWord) + " - " + ex.getMessage());
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
            logger.error(StringQuery.getUser(id) + " - " + ex.getMessage());
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
            logger.error(StringQuery.getAll() + " - " + ex.getMessage());
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
            logger.info(StringQuery.increase(id));
        } catch (SQLException | ClassNotFoundException ex) {
            logger.error(StringQuery.increase(id) + " - " + ex.getMessage());
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
            logger.info(StringQuery.decrease(id));
        } catch (SQLException | ClassNotFoundException ex) {
            logger.error(StringQuery.decrease(id) + " - " + ex.getMessage());
        } finally {
            DataSource.returnConnection(connect);
        }
    }

}
