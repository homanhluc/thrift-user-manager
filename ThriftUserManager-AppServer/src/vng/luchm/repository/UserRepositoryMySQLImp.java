/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

    @Override
    public void userRegister(User u) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.insert(u));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            DataSource.returnConnection(connect);
        }
        return u;
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void increase(String id) {
        try {
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.increase(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            DataSource.returnConnection(connect);
        }
    }

}
