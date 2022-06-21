package com.company;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Budget budget = new Budget();

    public void startMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose your action:\n" +
                               "1) Add income\n" +
                               "2) Add purchase\n" +
                               "3) Show list of purchases\n" +
                               "4) Balance\n" +
                               "0) Exit");
            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt();
            System.out.println();
            switch (action) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    showPurchases();
                    break;
                case 4:
                    showBalance();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    public void addIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter income:");
        double income = scanner.nextDouble();
        budget.addIncome(income);
        System.out.println("Income was added!\n");
    }

    public void addPurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        budget.addPurchase(name, price);
        System.out.println("Purchase was added!\n");
    }

    public void showPurchases() {
        Map<String, Double> purchases = budget.getPurchases();
        if (purchases.isEmpty()) {
            System.out.println("The purchase list is empty\n");
        } else {
            double total = 0.0;
            for (var entry : purchases.entrySet()) {
                System.out.printf("%s $%.2f%n", entry.getKey(), entry.getValue());
                total += entry.getValue();
            }
            System.out.printf("Total sum: %.2f%n%n", total);
        }
    }

    public void showBalance() {
        System.out.printf("Balance: $%.2f%n%n", budget.getBalance());
    }


}
