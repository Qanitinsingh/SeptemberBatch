package com.java.basics;

public class LearnVariables {

    // instance variable
    static String userName = "Global Nitin";


    public static void main(String[] args) {
        printGlobalVariableValue();
        printLocalVariableValue();

    }


    public static void printGlobalVariableValue() {

        System.out.println(userName);
    }



    public static void printLocalVariableValue() {

        String userName = "Local Nitin";
        System.out.println(userName);
    }
}