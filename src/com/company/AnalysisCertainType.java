package com.company;

import java.util.*;

public class AnalysisCertainType extends Analysis {
    Purchases purchases;
    String type;

    public AnalysisCertainType(String type) {
        this.purchases = Purchases.getInstance();
        this.type = type;
    }

    @Override
    List<Purchase> getSortedList() {
        Map<String, Purchase> purchaseMap = purchases.getPurchaseMap();
        List<Purchase> purchaseList = new ArrayList<>();
        for (var purchase : purchaseMap.values()) {
            if (type.equals(purchase.getType().getName())) {
                purchaseList.add(purchase);
            }
        }
        Collections.sort(purchaseList);
        return purchaseList;
    }
}
