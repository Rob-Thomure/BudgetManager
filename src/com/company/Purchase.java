package com.company;

public class Purchase implements Comparable<Purchase> {
    private final String name;
    private final double price;
    private final PurchaseType type;

    public Purchase(String name, double price, PurchaseType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public PurchaseType getType() {
        return type;
    }

    @Override
    public int compareTo(Purchase otherPurchase) {
        if (this.price == otherPurchase.getPrice()) {
            return 0;
        } else if (this.price > otherPurchase.getPrice()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        if (price > 0) {
            return String.format("%s $%.2f", name, price);
        } else {
            return String.format("%s $0", name);
        }

    }
}