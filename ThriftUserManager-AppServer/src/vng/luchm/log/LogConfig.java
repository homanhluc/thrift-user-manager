/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import vng.luchm.repository.UserRepositoryMySQLImp;

/**
 *
 * @author luchm
 */
public class LogConfig {
//    public LogConfig() {
//         Properties p = new Properties();
//         p.setProperty("log4j.rootLogger", "DEBUG, FILE ");
//         p.setProperty("log4j.appender.FILE", "org.apache.log4j.RollingFileAppender");
//         p.setProperty("log4j.appender.FILE.File", "src/vng/luchm/log/logs/log.bat");
//         p.setProperty("log4j.appender.FILE.ImmediateFlush", "true");
//         p.setProperty("log4j.appender.FILE.Threshold", "debug" );
//         p.setProperty("log4j.appender.FILE.Append", "true");
//         p.setProperty("log4j.appender.FILE.MaxFileSize", "1024KB");
//         p.setProperty("log4j.appender.FILE.MaxBackupIndex", "2");
//         p.setProperty("log4j.appender.FILE.layout", "org.apache.log4j.PatternLayout");
//         p.setProperty("log4j.appender.FILE.layout.conversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
//         PropertyConfigurator.configure(p);
//    }

//    private static final String _PATH_MAIN = "src/vng/luchm/log/logs/";
//    private static final String _PATH_BACKUP = "src/vng/luchm/log/logs/backup/";
//
//    private UserRepositoryMySQLImp urmsqli = new UserRepositoryMySQLImp();
//
//    public void readLog() {
//        try {
//
//            File f = new File(_PATH_MAIN, nameFileLog());
//            if (!f.exists()) {
//                f.createNewFile();
//            }
//            BufferedReader br = new BufferedReader(new FileReader(f));
//            String line;
//            boolean err = false;
////            while ((line = br.readLine()) != null) {
////                //Stock s = new Stock(line);
////                if (s.getStatus().equals("ERROR")) {
////                    urmsqli.logQuery(s.getStatement());
////                    System.out.println("[FIXED]");
////                    err = true;
////                }
////            }
//            if (err == true) {
//                f.delete();
//            }
//            br.close();
//        } catch (Exception ex) {
//            System.out.println("Loi doc file: " + ex);
//        }
//    }
//
//    public static void saveLog(String status, String classlog, String statement) {
//
//        try {
//            File f = new File(_PATH_MAIN, nameFileLog());
//            //Backup file neu >= 1MB
//            if (f.length() >= 1048576) {
//                Path source = Paths.get(_PATH_MAIN);
//                Path destination = Paths.get(_PATH_BACKUP);
//                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
//                f.delete();
//            }
//            // Save log
//            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
//            Stock s = new Stock(status, classlog, statement);
//            bw.write(s.toString());
//            bw.newLine();
//            bw.flush();
//            bw.close();
//        } catch (IOException ex) {
//            System.out.println("Loi doc file: " + ex);
//        }
//
//    }
//
//    private static String nameFileLog() {
//        DateFormat date = new SimpleDateFormat("yyyyMMdd");
//        Date dates = new Date();
//        return date.format(dates) + ".bat";
//    }
}
