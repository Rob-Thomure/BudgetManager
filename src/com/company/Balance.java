package com.company;

public class Balance {
    private static Balance instance = null;
    private double accountBalance;

    private Balance() {
        this.accountBalance = 0.0;
    }

    public static Balance getInstance() {
        if (instance ==null) {
            instance = new Balance();
        }
        return instance;
    }

    public void addIncome(double income) {
        this.accountBalance += income;
    }

    public void deductBalance(double price) {
        this.accountBalance -= price;
        if (accountBalance < 0.0) {
            accountBalance = 0.0;
        }
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }
}