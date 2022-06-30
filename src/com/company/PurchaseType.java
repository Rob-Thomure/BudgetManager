package com.company;

public enum PurchaseType {
    FOOD(1, "Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4, "Other"),
    ALL(5, "All");

    private final int num;
    private final String type;

    PurchaseType(int num, String type) {
        this.num = num;
        this.type = type;
    }

    public String getName() {
        return type;
    }

    public static PurchaseType getPurchaseType(int num) {
        for (PurchaseType value : values()) {
            if (value.num == num) {
                return value;
            }
        }
        return null;
    }
}