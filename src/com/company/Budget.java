package com.company;

import java.util.*;

public class Budget {
    private double balance;
    private final Map<String, Double> purchases;

    public Budget() {
        this.balance = 0.0;
        this.purchases = new LinkedHashMap<>();
    }

    public void addIncome(double income) {
        if (income > 0.0) {
            this.balance += income;
        }
    }

    public void addPurchase(String name, double price) {
        purchases.put(name, price);
        balance -= price;
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, Double> getPurchases() {
        return purchases;
    }

}
