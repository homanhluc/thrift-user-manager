/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 *
 * @author luchm
 */
public class LRUCacheLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;
    private int size;

    private LRUCacheLinkedHashMap(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    public static <K, V> LRUCacheLinkedHashMap<K, V> newInstance(int size) {
        return new LRUCacheLinkedHashMap<K, V>(size);
    }

    public void setMaxSize(int size) {
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > size;
    }
//
//    public static void main(String[] args) {
//        LRUCache<String, User> lru = LRUCache.newInstance(10);
//        User u1 = new User("1", "fsf", "gdf", 0, "gsg", "sd");
//        User u2 = new User("2", "fsf", "gdf", 0, "gsg", "sd");
//        User u3 = new User("3", "fsf", "gdf", 0, "gsg", "sd");
//        User u4 = new User("4", "fsf", "gdf", 0, "gsg", "sd");
//        User u5 = new User("5", "fsf", "gdf", 0, "gsg", "sd");
//        lru.put(u1.getId(), u1);
//        lru.put(u2.getId(), u2);
//        lru.put(u3.getId(), u3);
//        lru.put(u1.getId(), u1);
//        lru.put(u5.getId(), u5);
//        lru.put(u2.getId(), u2);
//        
//        System.out.println(lru);
//
//    }
}
