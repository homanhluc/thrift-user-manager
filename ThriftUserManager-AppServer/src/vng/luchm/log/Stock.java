/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author luchm
 */
public class Stock implements Serializable{
    
    
    private String date;
    private String time;
    private String status;
    private String classlog;
    private String statement;
    private String id;

//    public Stock(String line){
//        String[] split = line.split("::");
//        date = split[0];
//        time = split[1];
//        status = split[2];
//        classlog = split[3];
//        statement = split[4];
//    }
    public Stock(String status, String classlog, String statement, String id) {
        DateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat times = new SimpleDateFormat("HH:mm:ss");
        Date datess = new Date();
        this.date = dates.format(datess);
        this.time = times.format(datess);
        this.status = status;
        this.classlog = classlog;
        this.statement = statement;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClasslog() {
        return classlog;
    }

    public void setClasslog(String classlog) {
        this.classlog = classlog;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return date + "::" + time + "::" + status + "::" + classlog + "::" + statement + "::" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
