/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.pool.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luchm
 */
public class JDBCConnectionPool {

    // Danh sach luu tru cac ket noi
    private List<Connection> availableConnections = new ArrayList<Connection>();

    // Tao mot ket noi moi voi cac cau hinh co san
    private Connection createNewConnectionForPool() {
        // Lay cac Object de cau hinh csdl
        Configuration config = Configuration.getInstance();
        try {
            //nap DatabaseDriver cho Class.forName
            Class.forName(config.DB_DRIVER);
            // Tao ket noi moi bang DriverManager
            Connection connection = (Connection) DriverManager.getConnection(
                    config.DB_URL, config.DB_USER_NAME, config.DB_PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Khoi tao mot connection pool moi neu no chua full 
    private void initializeConnectionPool() {
        while (!checkIfConnectionPoolIsFull()) {
            availableConnections.add(createNewConnectionForPool());
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {
        final int MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTIONS;
        // Kiem tra SIZE ket noi co san vs thiet lap mat dinh
        if (availableConnections.size() < MAX_POOL_SIZE) {
            return false;
        }
        return true;
    }

    // Lay mot ket noi tu connection pool
    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;
        if (availableConnections.size() > 0) {
            connection = (Connection) availableConnections.get(0);
            availableConnections.remove(0);
        }
        return connection;
    }

    // Tra ve mot ket noi cho connection pool
    public synchronized void returnConnectionToPool(Connection connection) {
        availableConnections.add(connection);
    }

    // khoi tao connection pool trong constructor
    public JDBCConnectionPool() {
        initializeConnectionPool();
    }
}
