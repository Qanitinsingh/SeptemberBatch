package com.java.basics;

public class ControlStatementDemo {

    public static void main(String[] args) {

        learnIf();
        learnIfElse();
        learnElseIf();

    }


    public static void learnIf(){
        boolean testCasePass=true;

        if(testCasePass )
        {
            System.out.println("Test case is passed");
        }

    }

    public static void learnIfElse(){


        boolean testCasePass=true;

        if(testCasePass )
        {
            System.out.println("Test case is passed");
        }
        else
        {
            System.out.println("Test case is failed");
        }

    }
    public static void learnElseIf(){

        int bugCount=2;
        if (bugCount==0)
        {
            System.out.println("No major bugs");
        }
        else if (bugCount <=5)
        {
            System.out.println("Minor bugs indentified");
        }
        else if (bugCount <=10)
        {
            System.out.println("Major bugs indentified");
        }
        else
        {
            System.out.println("Application is in bad state");
        }
    }

    public static void learnNestedIf(){

    }

    public static void learnWhileLoop(){

    }

    public static void learnDoWhileLoop(){

    }
    public static void learnForLoop(){

    }
    public static void learnForEachLoop(){

    }
    public static void learnSwitchCase(){

    }

    public static void learnBreakAndContinue(){

    }






}
