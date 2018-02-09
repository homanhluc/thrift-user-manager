/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.pool;

/**
 *
 * @author luchm
 */
public class Configuration {

    public String DB_USER_NAME;
    public String DB_PASSWORD;
    public String DB_URL;
    public String DB_DRIVER;
    public Integer DB_MAX_CONNECTIONS;
    
    // Cau hinh ket noi MySQL
    private void init() {
        DB_USER_NAME = "root";
        DB_PASSWORD = "1995";
        DB_URL = "jdbc:mysql://localhost:3306/usermanager";
        DB_DRIVER = "com.mysql.jdbc.Driver";
        DB_MAX_CONNECTIONS = 5;
    }
    
    // Cau hinh nhu Singleton
    private static Configuration configuration = new Configuration();
    public static Configuration getInstance() {
        return configuration;
    }
    
    // Goi phuong thuc init trong constructor
    public Configuration() {
        init();
    }
}
