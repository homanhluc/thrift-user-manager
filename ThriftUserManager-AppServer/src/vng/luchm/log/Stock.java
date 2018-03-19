/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.log;

import java.io.Serializable;

/**
 *
 * @author luchm
 */
public class Stock {
    
    private String date;
    private String time;
    private String status;
    private String classlog;
    private String statement;

    public Stock(String line){
        String[] split = line.split("::");
        date = split[0];
        time = split[1];
        status = split[2];
        classlog = split[3];
        statement = split[4];
    }
    public Stock(String date, String time, String status, String classlog, String statement) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.classlog = classlog;
        this.statement = statement;
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
        return date + "::" + time + "::" + status + "::" + classlog + "::" + statement;
    }
}
