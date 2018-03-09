/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.main;

import vng.luchm.config.JettyServer;
import vng.luchm.config.ThriftClient;

/**
 *
 * @author luchm
 */
public class WebServerMain {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            ThriftClient.openSocket();
            JettyServer.start();
            System.out.println("Starting the wed server...");
        } catch (Exception ex) {
            System.out.println("WEB SERVER ERROR");
        }
    }
}
