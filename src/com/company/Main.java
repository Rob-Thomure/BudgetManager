package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, Double> purchases = getPurchases();
        printPurchases(purchases);
        printTotalCost(purchases);
    }

    public static void printPurchases(Map<String, Double> purchases) {
        for (var purchase : purchases.entrySet()) {
            System.out.printf("%s $%.2f%n", purchase.getKey(), purchase.getValue());
        }
        System.out.println();
    }

    public static void printTotalCost(Map<String, Double> purchases) {
        Double total = 0.0;
        for (Double purchase : purchases.values()) {
            total += purchase;
        }
        System.out.printf("Total: $%.2f", total);
    }

    public static Map<String, Double> getPurchases() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> purchases = new LinkedHashMap<>();
        while (true) {
            try {
                String[] purchase = scanner.nextLine().split("\\$");
                if ("EOF".equals(purchase[0])) {
                    break;
                }
                purchases.put(purchase[0].trim(), Double.parseDouble(purchase.length == 1 ? "0.0" : purchase[1]));
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return purchases;
    }
}
