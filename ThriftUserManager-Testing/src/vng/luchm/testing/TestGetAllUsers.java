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
import vng.luchm.domain.Statistics;

/**
 *
 * @author luchm
 */
public class TestGetAllUsers {

    private final int size = 1000;
    private final String USER_AGENT = "Mozilla/5.0";
    public static Statistics s = new Statistics();
    public static List<Statistics> listStatisticses = new ArrayList();
    public static double min = 0;
    public static double max = 0;
    public static double through = 0;

    private Statistics openConnection() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:9001/all");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        long starTime = System.currentTimeMillis();
        con.getInputStream();
        long endTime = System.currentTimeMillis();
        con.disconnect();

        Statistics s = new Statistics();
        s.setLoadTime(endTime - starTime);
        s.setResponseCode(con.getResponseCode());
        s.setResponseMessage(con.getResponseMessage());
        s.setSizeInByte(con.getContentLengthLong());

        return s;
    }

    public void sendingGetAllUsers() throws IOException {

        Statistics o = openConnection();
        s.setLoadTime(o.getLoadTime());
        s.setResponseCode(o.getResponseCode());
        s.setSizeInByte(o.getSizeInByte());
        s.setResponseMessage(o.getResponseMessage());

    }

    public void sendingGetAllUsers1000() {

        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Callable<Statistics>> listThread = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            listThread.add(new Callable<Statistics>() {
                @Override
                public Statistics call() throws Exception {
                    return openConnection();
                }
            });
        }
        try {
            List<Future<Statistics>> futures = executorService.invokeAll(listThread);
            executorService.shutdown();

            listStatisticses = new ArrayList<>();
            LinkedList<Double> list = new LinkedList<Double>();

            for (Future<Statistics> fut : futures) {
                listStatisticses.add(fut.get());
                list.add(fut.get().getLoadTime());
                through += fut.get().getLoadTime();
            }
            through = (1 / through) * 1000 * 3600;
            min = (double) Collections.min(list, null);
            max = (double) Collections.max(list, null);

        } catch (InterruptedException ex) {
            System.out.println("InterruptedException - " + ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println("ExecutionException - " + ex.getMessage());
        }

    }

}
