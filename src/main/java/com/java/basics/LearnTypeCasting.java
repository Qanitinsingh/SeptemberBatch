package com.java.basics;

import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public class LearnTypeCasting {


    // Widneing (Implicit / Soft operation) Type Casting - Small to Big

    // Order - byte -> short -> int -> long -> float -> double

    // Narrowing (Explicit / Forceful Operation) Type Casting

    // Big to small
    // Order - double -> float -> long -> int -> short -> byte


    public static void main(String[] args) {
        learnWidening();
        learnNarrowing();
    }

    public static void learnWidening() {

        int number = 123456;
        double myDouble = number; // Automatic casting: int to double

        System.out.println(number);      // Outputs 9
        System.out.println(myDouble);   // Outputs 9.0
    }

    public static void learnNarrowing() {

        double myDouble = 9.78;
        int myInt = (int) myDouble; // Manual casting: double to int

        System.out.println(myDouble);   // Outputs 9.78
        System.out.println(myInt);      // Outputs 9
    }
}
