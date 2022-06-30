package com.company;

public class Balance {
    private double accountBalance;

    public Balance() {
        this.accountBalance = 0.0;
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