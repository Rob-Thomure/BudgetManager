package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Data {
    private final File file;
    private final Purchases purchases;
    private final Balance balance;

    public Data() {
        this.file = new File("purchases.txt");
        this.purchases = Purchases.getInstance();
        this.balance = Balance.getInstance();
    }

    public void save() {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.printf("%.2f%n", balance.getAccountBalance());
            Map<String, Purchase> purchaseMap = purchases.getPurchaseMap();
            for (var entry : purchaseMap.entrySet()) {
                printWriter.println(entry.getValue().getName());
                printWriter.println(entry.getValue().getPrice());
                printWriter.println(entry.getValue().getType());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("file not found: %s", file);
        }

    }

    public void load() {

        try (Scanner scanner = new Scanner(file)) {
            balance.addIncome(Double.parseDouble(scanner.nextLine()));
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                PurchaseType purchaseType = PurchaseType.valueOf(scanner.nextLine());
                purchases.addPurchase(name, price, purchaseType);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("file not found: %s", file);
        }
    }
}
