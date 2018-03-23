/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.main;
 
import vng.luchm.cache.LRUCacheHashMap;
import vng.luchm.cache.LRUCacheLinkedHashMap;
import vng.luchm.config.JettyServer;
import vng.luchm.config.ThriftClient;

/**
 *
 * @author luchm
 */
public class WebServerMain {
    
    public static LRUCacheLinkedHashMap caches = LRUCacheLinkedHashMap.newInstance(4);
    public static LRUCacheHashMap cache = LRUCacheHashMap.newInstance(3);
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            ThriftClient.openSocket();
            JettyServer.start();
            System.out.println("Starting the wed server...");
        } catch (Exception ex) {
            System.out.println("WEB SERVER ERROR::"+ex);
        }
    }
}
