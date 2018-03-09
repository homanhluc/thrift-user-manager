/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.main;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import vng.luchm.controller.RequestStatistics;
import vng.luchm.testing.TestGetAllUsers;
/**
 *
 * @author luchm
 */
public class ThriftUserManagerTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(9002);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(RequestStatistics.class, "");
        server.start();
        TestGetAllUsers testGetAllUsers = new TestGetAllUsers();
        testGetAllUsers.sendingGetAllUsers();
    }
}
