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
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author luchm
 */
public class TestPostScore {
    private final int size = 1000;
    private final String USER_AGENT = "Mozilla/5.0";

    private Long openConnection() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:9001/score?id=3&set=in");
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
       
        long starTime = System.currentTimeMillis();
        con.getInputStream();
        long endTime = System.currentTimeMillis();
        return (endTime - starTime);
    }

    public void sendingPostScore() {
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
            for (Future<String> fut : futures) {
                System.out.println(fut.get());
            }
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException - " + ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println("ExecutionException - " + ex.getMessage());
        }

    }
}
