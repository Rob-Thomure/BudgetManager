package com.company;

import java.util.*;

public class AnalysisAll extends Analysis {
    Purchases purchases;


    public AnalysisAll() {
        this.purchases = Purchases.getInstance();
    }

    @Override
    public List<Purchase> getSortedList() {
        Map<String, Purchase> purchaseMap = purchases.getPurchaseMap();
        List<Purchase> purchaseList = new ArrayList<>(purchaseMap.values());
        Collections.sort(purchaseList);
        return purchaseList;
    }
}
