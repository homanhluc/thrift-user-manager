/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.log;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import vng.luchm.repository.UserRepositoryMySQLImp;

/**
 *
 * @author luchm
 */
public class LogBinary {

    private static final String _PATH_MAIN = "src/vng/luchm/log/logs/";
    private static final String _PATH_BACKUP = "src/vng/luchm/log/logs/backup/";
    private static UserRepositoryMySQLImp urmsqli = new UserRepositoryMySQLImp();

    private static File f = null;

    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;

    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;

    //private static List<Stock> logList = new LinkedList();
    public static void writeLog(String status, String classlog, String statement, String id) {
        try {
            f = new File(_PATH_MAIN + nameFileLog());
//            if (!f.exists()) {
//                File fp = new File(_PATH_MAIN + nameFileLogPrev());
//                readLog(fp);
//            }
            //Backup file neu >= 1MB
            if (f.length() >= 1048576) {
                try {
                    Path source = Paths.get(_PATH_MAIN);
                    Path destination = Paths.get(_PATH_BACKUP);
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    f.delete();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

            Stock stock = new Stock(status, classlog, statement, id);
            fos = new FileOutputStream(f, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(stock);
            oos.flush();
            oos.close();
            fos.close();
            //logList.add(stock);

//            if (logList.size() == 10) {
//
//                for (Stock s : logList) {
//                    
//                }
//                logList = new LinkedList();
//            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void readLog() {
        try {
            f = new File(_PATH_MAIN + nameFileLog());

            fis = new FileInputStream(f);
            Stock stock;
            HashMap<String, Stock> hm = new HashMap();
            
            while (true) {
                try {
                    ois = new ObjectInputStream(fis);
                    stock = (Stock) ois.readObject();
                    System.out.println(stock.toString());
                    if (stock.getStatus().equals("WARNING")) {
                        hm.put(stock.getId(), stock);
                    }
                    if (stock.getStatus().equals("SUCCESS")) {
                        if (hm.get(stock.getId()) != null) {
                            hm.remove(stock.getId());
                        }
                    }
                } catch (EOFException ex) {
                    break;
                } catch (IOException ex) {
                    System.out.println(ex);
                    break;
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                    break;
                }
            }
            if (hm.size() > 0) {
                for (Map.Entry<String, Stock> entry : hm.entrySet()) {
                    urmsqli.logQuery(entry.getValue().getStatement());
                    writeLog("SUCCESS", LogBinary.class.toString(), entry.getValue().getStatement(), entry.getValue().getId());
                    System.out.println("Update done!");
                }
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static String nameFileLog() {
        DateFormat date = new SimpleDateFormat("yyyyMMdd");
        Date dates = new Date();
        return date.format(dates) + ".bat";
    }

    private static String nameFileLogPrev() {
        DateFormat date = new SimpleDateFormat("yyyyMMdd");
        Date dates = new Date();
        dates.setDate(dates.getDate() - 1);
        return date.format(dates) + ".bat";
    }

    public static void main(String[] args) {
        readLog();
    }
}
