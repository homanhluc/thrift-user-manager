/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.bson.Document;

/**
 *
 * @author luchm
 */
public class ConnectionPool {

    private static DataSource dataSource;

    static {
        initPool();
    }

    public static void initPool() {
        DriverAdapterCPDS cpds = new DriverAdapterCPDS();
        try {
            cpds.setDriver(SystemInfo.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver fail!");
            e.printStackTrace();
        }
        cpds.setUrl(SystemInfo.HOST + "/" + SystemInfo.DB + "?useUnicode=true&characterEncoding=utf-8");
        cpds.setUser(SystemInfo.USERNAME);
        cpds.setPassword(SystemInfo.PASSWORD);

        SharedPoolDataSource spds = new SharedPoolDataSource();
        spds.setConnectionPoolDataSource(cpds);
        spds.setMaxActive(1000); // max connection mongodb at the same time
        spds.setMaxWait(-1); // maximum number of milliseconds that the pool will wait, -1 is wait indefinitely
        dataSource = spds;
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

    public static Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            initPool();
            e.printStackTrace();
            return dataSource.getConnection();
        }
    }
}
