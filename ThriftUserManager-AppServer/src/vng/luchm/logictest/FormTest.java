/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.logictest;

/**
 *
 * @author luchm
 */
public class FormTest {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public FormTest(String input, String output, String result) {
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        
        if (result.equals(output)) {
            System.out.println("Result: " + true);
        } else {
            System.out.println("Result: " + ANSI_RED + false + ANSI_RESET);
        }
        
        System.out.println("-------------------------");
    }
}
