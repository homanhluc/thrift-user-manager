/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luchm
 */
// Lop nay de nhan ket noi va tra ve mot ket noi
public class DataSource {

    static JDBCConnectionPool pool = new JDBCConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = pool.getConnectionFromPool();
        return connection;
    }

    public static void closeConnection(ResultSet resultset, Statement statement, Connection connect) {
        try {
            if (resultset != null) {
                resultset.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void returnConnection(Connection connection) {
        pool.returnConnectionToPool(connection);
    }
}
