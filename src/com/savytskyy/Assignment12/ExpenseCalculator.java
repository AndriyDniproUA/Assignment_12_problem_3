package com.savytskyy.Assignment12;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpenseCalculator {
    Scanner sc;

    public ExpenseCalculator(Scanner sc) {
        this.sc = sc;
    }

    private Map<String, Double> incomeRegister = new HashMap<>();
    private Map<String, Double> expenseRegister = new HashMap<>();
    private double totalIncome = 0;
    private double totalExpense = 0;


    Pattern pattern = Pattern.compile("([+-]\\d+)\\s(.+)");

    void run() {
        processInput();
        showTotal();
        showExpenses();
        showIncome();
    }

    void processInput() {
        System.out.println("Welcome to your new ExpenseCalculator!");
        System.out.println("Please enter your income and expenses in the following format:");
        System.out.println("* for income type: +nnn source");
        System.out.println("* for expenses type: -nnn purpose");
        System.out.println("To finish input enter \"END\"\n");

        while (true) {
            System.out.print("Enter your input here: ");
            String input = sc.nextLine();
            if (input.equals("END")) break;
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String key = matcher.group(2);
                double value = Double.parseDouble(matcher.group(1));
                if (value > 0) addIncomeItem(key, value);
                if (value < 0) addExpenseItem(key, value);
            }
        }
    }

    private void addIncomeItem(String key, Double value) {
        if (!incomeRegister.containsKey(key)) {
            incomeRegister.put(key, value);
        } else {
            double cur = incomeRegister.get(key);
            incomeRegister.put(key, cur + value);
        }
        totalIncome += value;
    }

    private void addExpenseItem(String key, Double value) {
        if (!expenseRegister.containsKey(key)) {
            expenseRegister.put(key, value);
        } else {
            double cur = expenseRegister.get(key);
            expenseRegister.put(key, cur + value);
        }
        totalExpense += value;
    }

    private void showTotal() {
        System.out.println("*****************");
        System.out.println("The TOTAL IS: " + (totalExpense + totalIncome));
    }

    private void showExpenses() {
        System.out.println("\nTotal expenses are: " + totalExpense + " including:");
        for (Map.Entry<String, Double> entry : expenseRegister.entrySet()) {
            System.out.println("   " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private void showIncome() {
        System.out.println("\nTotal income is: " + totalIncome + " including:");
        for (Map.Entry<String, Double> entry : incomeRegister.entrySet()) {
            System.out.println("   " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
