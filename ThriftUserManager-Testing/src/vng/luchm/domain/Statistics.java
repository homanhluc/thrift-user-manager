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

    private String methodName;
    private int totalReq;
    private int min;
    private int max;
    private double throughPut;
    private double loadTime;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getTotalReq() {
        return totalReq;
    }

    public void setTotalReq(int totalReq) {
        this.totalReq = totalReq;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getThroughPut() {
        return throughPut;
    }

    public void setThroughPut(double throughPut) {
        this.throughPut = throughPut;
    }

    public double getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(double loadTime) {
        this.loadTime = loadTime;
    }

}
