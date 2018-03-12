/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.domain;

/**
 *
 * @author luchm
 */
public class Statistics {

    private int totalReq;
    private double loadTime;
    private double sizeInByte;
    private int responseCode;
    private String responseMessage;

    public int getTotalReq() {
        return totalReq;
    }

    public void setTotalReq(int totalReq) {
        this.totalReq = totalReq;
    }
    

    public double getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(double loadTime) {
        this.loadTime = loadTime;
    }

    public double getSizeInByte() {
        return sizeInByte;
    }

    public void setSizeInByte(double sizeInByte) {
        this.sizeInByte = sizeInByte;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
}
