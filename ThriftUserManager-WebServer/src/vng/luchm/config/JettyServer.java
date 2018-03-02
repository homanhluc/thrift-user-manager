/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.config;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import vng.luchm.controller.Login;
import vng.luchm.controller.Register;
import vng.luchm.controller.SetScore;

/**
 *
 * @author luchm
 */
public class JettyServer {

    private static Server server;

    public static void start() throws Exception {

        int maxThreads = 1000;
        int minThreads = 10;
        int idleTimeout = 120;

        QueuedThreadPool threadPool;
        threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(9001);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(Register.class, "/register");
        servletHandler.addServletWithMapping(Login.class, "/login");
        servletHandler.addServletWithMapping(SetScore.class, "/score");

        server.start();

    }

    public static void stop() throws Exception {
        server.stop();
    }
}
