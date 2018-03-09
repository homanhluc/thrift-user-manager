/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.testing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import vng.luchm.domain.Statistics;

/**
 *
 * @author luchm
 */
public class TestGetAllUsers {

    private final int size = 1000;
    private final String USER_AGENT = "Mozilla/5.0";
    public static Statistics s = new Statistics();

    private Long openConnection() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:9001/all");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        long starTime = System.currentTimeMillis();
        con.getInputStream();
        long endTime = System.currentTimeMillis();
        con.disconnect();
        return (endTime - starTime);
    }
    public void sendingGetAllUsers() {
        int i = 0;
        s.setMethodName("sendingGetAllUsers()");
        while(true) {
            try {
                s.setTotalReq(i++);
                if(openConnection() < s.getMin()) {
                    s.setMin(Integer.parseInt(openConnection().toString()));
                }
                if(openConnection() > s.getMax()) {
                    s.setMax(Integer.parseInt(openConnection().toString()));
                }
                //System.out.println(openConnection() + " - "+ i++);
            } catch (IOException ex) {
                System.out.println("IOException - "+ ex.getMessage());
            }
        }
    }

    public void sendingGetAllUsers1000() {

        s.setMethodName("getAllUsers()");
        s.setTotalReq(size);

        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Callable<String>> listThread = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            listThread.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return openConnection().toString();
                }
            });
        }
        try {
            List<Future<String>> futures = executorService.invokeAll(listThread);
            executorService.shutdown();
            LinkedList<Integer> list = new LinkedList<Integer>();
            for (Future<String> fut : futures) {
                list.add(Integer.parseInt(fut.get()));
            }
            int min = (int) Collections.min(list, null);
            int max = (int) Collections.max(list, null);
            s.setMin(min);
            s.setMax(max);

        } catch (InterruptedException ex) {
            System.out.println("InterruptedException - " + ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println("ExecutionException - " + ex.getMessage());
        }

    }

}
