/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.cache;

import java.util.HashMap;
import vng.luchm.thrift.User;

/**
 *
 * @author luchm
 */
public class LRUCacheHashMap {

    class Node {

        int key;
        User value;
        Node pre;
        Node next;

        public Node(int key, User value) {
            this.key = key;
            this.value = value;
        }
    }
    
    int capacity;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    Node head=null;
    Node end=null;
 
    private LRUCacheHashMap(int capacity) {
        this.capacity = capacity;
    }
    public static LRUCacheHashMap newInstance(int size) {
        return new LRUCacheHashMap(size);
    }
    public User get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
 
        return null;
    }
 
    public void remove(Node n){
        if(n.pre!=null){
            n.pre.next = n.next;
        }else{
            head = n.next;
        }
 
        if(n.next!=null){
            n.next.pre = n.pre;
        }else{
            end = n.pre;
        }
 
    }
 
    public void setHead(Node n){
        n.next = head;
        n.pre = null;
 
        if(head!=null)
            head.pre = n;
 
        head = n;
 
        if(end ==null)
            end = head;
    }
 
    public void set(int key, User value) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        }else{
            Node created = new Node(key, value);
            if(map.size()>=capacity){
                map.remove(end.key);
                remove(end);
                setHead(created);
 
            }else{
                setHead(created);
            }    
 
            map.put(key, created);
        }
    }
    public void getAll() {
        System.out.println(map);
    }
}
