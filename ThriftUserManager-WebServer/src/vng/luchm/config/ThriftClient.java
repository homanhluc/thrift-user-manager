/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import vng.luchm.thrift.UserManagerService;

/**
 *
 * @author luchm
 */
public class ThriftClient {

    private static TTransport transport;
    private static TProtocol protocol;
    public static UserManagerService.Client client;

    public static void openSocket() {
        try {
            transport = new TSocket("127.0.0.1", 9000);
            protocol = new TBinaryProtocol(transport);
            client = new UserManagerService.Client(protocol);
            transport.open();
        } catch (TTransportException ex) {
            Logger.getLogger(ThriftClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
