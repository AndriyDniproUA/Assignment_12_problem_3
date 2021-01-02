package com.savytskyy.Assignment12;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	ExpenseCalculator calculator = new ExpenseCalculator(sc);

	calculator.run();



    }
}
