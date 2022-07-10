package com.company;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Purchases purchases = Purchases.getInstance();
    private final Balance balance = Balance.getInstance();
    private final Data data = new Data();

    public void startMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose your action:\n" +
                               "1) Add income\n" +
                               "2) Add purchase\n" +
                               "3) Show list of purchases\n" +
                               "4) Balance\n" +
                               "5) Save\n" +
                               "6) Load\n" +
                               "7) Analyze (Sort)\n" +
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
                    if (purchases.isEmpty()) {
                        System.out.println("The purchase list is empty!\n");
                    } else {
                        showPurchases();
                    }
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                    data.save();
                    System.out.println("Purchase were saved!\n");
                    break;
                case 6:
                    data.load();
                    System.out.println("Purchases were loaded!\n");
                    break;
                case 7:
                    showAnalysis();

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

    public void showAnalysis() {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            printAnalysisTypes();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Analysis analysisAll = new AnalysisAll();
                    printAnalysisResult(analysisAll, "All");
                    break;
                case 2:
                    Analysis analysisByTpe = new AnalysisByType();
                    printAnalysisResult(analysisByTpe, "Types");
                    break;
                case 3:
                    printPurchaseTypes();
                    chooseAnalysisTypePurchase();
                    break;
                case 4:
                    backToMainMenu = true;
                    System.out.println();
                    break;
            }
        }
    }

    public void chooseAnalysisTypePurchase() {
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        Analysis analysisCertainType;
        switch (type) {
            case 1:
                analysisCertainType = new AnalysisCertainType("Food");
                printAnalysisResult(analysisCertainType, "Food");
                break;
            case 2:
                analysisCertainType = new AnalysisCertainType("Clothes");
                printAnalysisResult(analysisCertainType, "Clothes");
                break;
            case 3:
                analysisCertainType = new AnalysisCertainType("Entertainment");
                printAnalysisResult(analysisCertainType, "Entertainment");
                break;
            case 4:
                analysisCertainType = new AnalysisCertainType("Other");
                printAnalysisResult(analysisCertainType, "Other");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    //TODO test this method , moved total inside else, added parameter type
    public void printAnalysisResult(Analysis analysis, String type) {
        List<Purchase> purchaseList = analysis.getSortedList();
        if (purchaseList.isEmpty()) {
            System.out.println("\nThe purchase list is empty!\n");
        } else {
            System.out.printf("%n%s:%n", type);
            for (Purchase purchase : purchaseList) {
                System.out.println(purchase);
            }
            double total = analysis.getTotalCost(purchaseList);
            if (total > 0) {
                System.out.printf("Total sum: $%.2f%n%n", total);
            } else {
                System.out.println("Total sum: $0\n");
            }
        }

    }

    public void addIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter income:");
        double income = scanner.nextDouble();
        balance.addIncome(income);
        System.out.println("Income was added!\n");
    }

    public void addPurchase() {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            Scanner scanner = new Scanner(System.in);
            printPurchaseTypes();
            int type = scanner.nextInt();
            if (type > 5) {
                System.out.println("Invalid input! please input 1-5");
                continue;
            } else if (type == 5) {
                System.out.println();
                backToMainMenu = true;
                continue;
            }
            PurchaseType purchaseType = PurchaseType.getPurchaseType(type);
            System.out.println();
            scanner.nextLine();
            System.out.println("Enter purchase name:");
            String name = scanner.nextLine();
            System.out.println("Enter its price:");
            double price = scanner.nextDouble();
            purchases.addPurchase(name, price, purchaseType);
            balance.deductBalance(price);
            System.out.println("Purchase was added!\n");
        }
    }

    public void printAnalysisTypes() {
        System.out.println("How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
    }

    public void printPurchaseTypes() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
    }

    public void printListTypes() {
        System.out.println("Choose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
    }



    public void showPurchases() {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            printListTypes();
            Scanner scanner = new Scanner(System.in);
            int type = scanner.nextInt();
            if (type > 6) {
                System.out.println("Invalid input! please input 1-6");
                continue;
            } else if (type == 6) {
                System.out.println();
                backToMainMenu = true;
                continue;
            }
            PurchaseType purchaseType = PurchaseType.getPurchaseType(type);
            Map<String, Double> purchaseMap = purchases.getPurchaseMap(purchaseType);
            System.out.println();
            assert purchaseType != null;
            System.out.printf("%s:%n", purchaseType.getName());

            if (purchaseMap.isEmpty()) {
                System.out.println("The purchase list is empty!\n");
            } else {
                double total = 0.0;
                for (var entry : purchaseMap.entrySet()) {
                    System.out.printf("%s $%.2f%n", entry.getKey(), entry.getValue());
                    total += entry.getValue();
                }
                System.out.printf("Total sum: $%.2f%n%n", total);
            }
        }
    }

    public void showBalance() {
        System.out.printf("Balance: $%.2f%n%n", balance.getAccountBalance());
    }
}