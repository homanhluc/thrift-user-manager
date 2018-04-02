/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.testing;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    public static Statistics get = new Statistics();
    public static Statistics post = new Statistics();
    public static List<Statistics> listStatisticses = new ArrayList();
    public static double min = 0;
    public static double max = 0;
    public static double through = 0;

    private Statistics openConnection() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:9001/user?id=1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        long starTime = System.currentTimeMillis();
        conn.getInputStream();
        long endTime = System.currentTimeMillis();
        conn.disconnect();

        Statistics s = new Statistics();
        s.setLoadTime(endTime - starTime);
        s.setResponseCode(conn.getResponseCode());
        s.setResponseMessage(conn.getResponseMessage());
        s.setSizeInByte(conn.getContentLengthLong());

        return s;
    }

    private Statistics openConnectionPost() throws MalformedURLException, IOException {
        String urlParameters = "id=3&set=in";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "http://localhost:9001/score";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);

        long starTime = System.currentTimeMillis();
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }
        long endTime = System.currentTimeMillis();
        conn.disconnect();

        Statistics s = new Statistics();
        s.setLoadTime(endTime - starTime);
        s.setResponseCode(conn.getResponseCode());
        s.setResponseMessage(conn.getResponseMessage());
        s.setSizeInByte(conn.getContentLengthLong());

        return s;
    }

    public void sendingGetAllUsers() throws IOException {
        Statistics o = openConnection();
        get.setLoadTime(o.getLoadTime());
        get.setResponseCode(o.getResponseCode());
        get.setSizeInByte(o.getSizeInByte());
        get.setResponseMessage(o.getResponseMessage());
    }
    public void sendingPostUsers() throws IOException {
        Statistics o = openConnectionPost();
        post.setLoadTime(o.getLoadTime());
        post.setResponseCode(o.getResponseCode());
        post.setSizeInByte(o.getSizeInByte());
        post.setResponseMessage(o.getResponseMessage());
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
