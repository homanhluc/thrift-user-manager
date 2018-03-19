/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.main;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import vng.luchm.handler.UserManagerServiceHandler;
import vng.luchm.log.LogConfig;
import vng.luchm.thrift.UserManagerService;

/**
 *
 * @author luchm
 */
public class AppServerMain {

    private static UserManagerServiceHandler handler;
    private static UserManagerService.Processor processor;
    private static TServerTransport serverTransport;
    private static TServer server;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException {
        handler = new UserManagerServiceHandler();
        processor = new UserManagerService.Processor(handler);
        
        LogConfig lc = new LogConfig();
        lc.readLog();
        Runnable threadServerStart = () -> {
            serverStart(processor);
        };
        new Thread(threadServerStart).start();
    }

    public static void serverStart(UserManagerService.Processor processor) {
        try {
            serverTransport = new TServerSocket(9000);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport)
                    .processor(processor)
                    .maxWorkerThreads(100)
                    .requestTimeout(120);
            server = new TThreadPoolServer(args);
            System.out.println("Starting the thread pool server...");

            server.serve();
        } catch (TTransportException ex) {
            ex.printStackTrace();
        }
    }
}
