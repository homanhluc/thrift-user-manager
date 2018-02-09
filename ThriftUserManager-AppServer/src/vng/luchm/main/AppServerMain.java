/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import vng.luchm.handler.UserManagerServiceHandler;
import vng.luchm.thrift.User;
import vng.luchm.thrift.UserManagerService;

/**
 *
 * @author luchm
 */
public class AppServerMain {
    public static UserManagerServiceHandler handler;
    public static UserManagerService.Processor processor;
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        handler = new UserManagerServiceHandler();
        processor = new UserManagerService.Processor(handler);
        Runnable threadServerStart = () -> {
            serverStart(processor);
        };
        new Thread(threadServerStart).start();
    }
    
    public static void serverStart(UserManagerService.Processor processor) {
        try {
            final TServerTransport serverTransport = new TServerSocket(9000);
            final TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor).maxWorkerThreads(1000));
            System.out.println("Starting the thread pool server...");
   
            server.serve();
        } catch (TTransportException ex) {
            ex.printStackTrace();
        }
    }
}
