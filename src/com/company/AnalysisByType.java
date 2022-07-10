package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalysisByType extends Analysis{
    Purchases purchases;

    public AnalysisByType() {
        this.purchases = Purchases.getInstance();
    }

    @Override
    List<Purchase> getSortedList() {
        Map<String, Purchase> purchaseMap = purchases.getPurchaseMap();
        double food = 0;
        double entertainment = 0;
        double clothes = 0;
        double other = 0;
        for (var purchase : purchaseMap.values()) {
            switch (purchase.getType().getName()) {
                case "Food":
                    food += purchase.getPrice();
                    break;
                case "Entertainment":
                    entertainment += purchase.getPrice();
                    break;
                case "Clothes":
                    clothes += purchase.getPrice();
                    break;
                case "Other":
                    other += purchase.getPrice();
                    break;
                default:
                    break;
            }
        }
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(new Purchase("Food -", food, PurchaseType.FOOD));
        purchaseList.add(new Purchase("Entertainment -", entertainment, PurchaseType.ENTERTAINMENT));
        purchaseList.add(new Purchase("Clothes -", clothes, PurchaseType.CLOTHES));
        purchaseList.add(new Purchase("Other -", other, PurchaseType.CLOTHES));
        return purchaseList;
    }
}
