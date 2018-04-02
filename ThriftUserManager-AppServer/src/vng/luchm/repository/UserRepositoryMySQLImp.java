/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import vng.luchm.config.StringQuery;
import vng.luchm.log.LogBinary;
import vng.luchm.log.LogConfig;
import vng.luchm.pool.mysql.DataSource;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class UserRepositoryMySQLImp implements IUserRepository {

    private Connection connect;
    private Statement statement;
    private ResultSet resultset;
    private final String _WARNING = "WARNING";
    private final String _SUCCESS = "SUCCESS";
    private final String _ERROR = "ERROR";
    //private static final Logger logger = Logger.getLogger(UserRepositoryMySQLImp.class);

    public UserRepositoryMySQLImp() {
        this.resultset = null;
    }

    @Override
    public boolean userRegister(User u) {
        try {
            if (u.getUserName() != null && u.getPassWord() != null && u.getUserName().matches("[^a-zA-Z0-9 ]")) {
                LogBinary.writeLog(_WARNING, UserRepositoryMySQLImp.class.toString(), StringQuery.insert(u), u.Id);
                connect = DataSource.getConnection();
                statement = connect.createStatement();
                int a = statement.executeUpdate(StringQuery.insert(u));

                if (a == 1) {
                    LogBinary.writeLog(_SUCCESS, UserRepositoryMySQLImp.class.toString(), StringQuery.insert(u), u.Id);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LogBinary.writeLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.insert(u), u.Id);
        } finally {
            DataSource.returnConnection(connect);
        }
        return false;
    }

    @Override
    public boolean userlogin(String userName, String passWord) {
        try {
            User u = new User();
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            resultset = statement.executeQuery(StringQuery.checkLogin(userName, passWord));

            while (resultset.next()) {
                u.setId(resultset.getString("Id"));
            }
            if (u.getId() != null) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            LogBinary.writeLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.checkLogin(userName, passWord), null);
        } finally {
            DataSource.returnConnection(connect);
        }
        return false;
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
            LogBinary.writeLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.getUser(id), id);
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
            LogBinary.writeLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.getAll(), null);
        } finally {
            DataSource.returnConnection(connect);
        }

        return list;
    }

    @Override
    public void increase(String id) {
        try {
            LogBinary.writeLog(_WARNING, UserRepositoryMySQLImp.class.toString(), StringQuery.increase(id), id);
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.increase(id));
            LogBinary.writeLog(_SUCCESS, UserRepositoryMySQLImp.class.toString(), StringQuery.increase(id), id);
        } catch (SQLException | ClassNotFoundException ex) {
            LogBinary.writeLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.increase(id), id);
        } finally {
            DataSource.returnConnection(connect);
        }
    }

    @Override
    public void decrease(String id) {
        try {
            LogBinary.writeLog(_WARNING, UserRepositoryMySQLImp.class.toString(), StringQuery.decrease(id), id);
            connect = DataSource.getConnection();
            statement = connect.createStatement();
            statement.executeUpdate(StringQuery.decrease(id));
            LogBinary.writeLog(_SUCCESS, UserRepositoryMySQLImp.class.toString(), StringQuery.decrease(id), id);
        } catch (SQLException | ClassNotFoundException ex) {
            LogBinary.writeLog(_ERROR, UserRepositoryMySQLImp.class.toString(), StringQuery.decrease(id), id);
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
