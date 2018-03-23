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
    public static void main(String[] args) {
        readLog();
    }
    public static void writeLog(String status, String classlog, String statement) {
        try {
            f = new File(_PATH_MAIN + nameFileLog());
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
            
            fos = new FileOutputStream(f, true);
            oos = new ObjectOutputStream(fos);
            Stock stock = new Stock(status, classlog, statement);
            oos.writeObject(stock);
            oos.flush();
            
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public static void readLog() {
        try {
            f = new File(_PATH_MAIN + nameFileLog());
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

            fis = new FileInputStream(f);
            Stock stock;
            boolean err = false;
            while (true) {
                try {
                    ois = new ObjectInputStream(fis);
                    stock = (Stock) ois.readObject();
                    System.out.println(stock.toString());
                    if (stock.getStatus().equals("ERROR")) {
                        urmsqli.logQuery(stock.getStatement());
                        err = true;
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
            if (err == true) {
                f.delete();
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
}
