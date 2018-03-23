/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.pool.mysql;

import java.sql.Connection;

/**
 *
 * @author luchm
 */
// Lop nay de nhan ket noi va tra ve mot ket noi
public class DataSource {

    static JDBCConnectionPool pool = new JDBCConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = pool.getConnectionFromPool();
        return connection;
    }
    public static void returnConnection(Connection connection) {
        pool.returnConnectionToPool(connection);
    }
}
