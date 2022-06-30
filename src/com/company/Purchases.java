package com.company;

import java.util.LinkedHashMap;
import java.util.Map;

public class Purchases {
    Map<String, Purchase> purchaseMap;

    Purchases() {
        this.purchaseMap = new LinkedHashMap<>();
    }

    public void addPurchase(String name, double price, PurchaseType type) {
        Purchase purchase = new Purchase(name, price, type);
        purchaseMap.put(name, purchase);
    }

    public Map<String, Double> getPurchaseMap(PurchaseType purchaseType) {
        Map<String, Double> purchases = new LinkedHashMap<>();
        for (var entry : purchaseMap.entrySet()) {
            if (entry.getValue().getType().equals(purchaseType) || purchaseType.equals(PurchaseType.ALL)) {
                purchases.put(entry.getValue().getName(), entry.getValue().getPrice());
            }
        }
        return purchases;
    }

    public boolean isEmpty() {
        return purchaseMap.isEmpty();
    }
}