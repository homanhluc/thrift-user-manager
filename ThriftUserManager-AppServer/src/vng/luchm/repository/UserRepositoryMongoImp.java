/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.repository;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import org.slf4j.Logger;
import vng.luchm.config.SystemInfo;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class UserRepositoryMongoImp implements IUserRepository {

//    private static final MongoClient mongoClient
//            = new MongoClient(SystemInfo.HOST, 2330);
//    private static final MongoDatabase db
//            = mongoClient.getDatabase(SystemInfo.DB);
//    private static final MongoCollection<Document> coll
//            = db.getCollection("user");
//    private static final MongoClientOptions.Builder clientOptions = new MongoClientOptions.Builder();
// 
    @Override
    public boolean userRegister(User userInfo) {
//        try {
//            User user = new User();
//            Hashtable ht = new Hashtable();
//            ht.put(user, userInfo);
//            Gson gson = new Gson();
//            String json = gson.toJson(ht);
//            Document doc = gson.fromJson(json, Document.class);
//            coll.insertOne(doc);
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            e.printStackTrace();
//        }
        return true;
    }

    @Override
    public void increase(String id) {

    }

    @Override
    public void decrease(String id) {

    }

    @Override
    public boolean userlogin(String userName, String passWord) {
        return true;
    }

    @Override
    public User getUserById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
