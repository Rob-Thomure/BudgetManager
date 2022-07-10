package com.company;

import java.util.List;

public abstract class Analysis {

    abstract List<Purchase> getSortedList();

    public double getTotalCost(List<Purchase> purchases) {
        double total = 0;
        for (Purchase purchase : purchases) {
            total += purchase.getPrice();
        }
        return total;
    }
}
