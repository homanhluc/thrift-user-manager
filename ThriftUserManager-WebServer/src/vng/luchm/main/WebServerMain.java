/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.main;

import java.util.logging.Level;
import java.util.logging.Logger;
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
        } catch (Exception ex) {
            System.err.println("Loi");
            Logger.getLogger(WebServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
