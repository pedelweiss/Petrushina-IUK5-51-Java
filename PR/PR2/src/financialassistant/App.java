package src.financialassistant;

import src.financialassistant.manager.FinancialManager;
import src.financialassistant.service.*;
import src.financialassistant.ui.ConsoleMenu;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите формат хранения данных:");
        System.out.println("1. Текстовый файл (TXT)");
        System.out.println("2. JSON файл");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();

        DataStorageService storageService;
        if (choice == 1) {
            storageService = new TextFileStorageService();
        } else {
            storageService = new JsonStorageService();
        }

        FinancialManager manager = new FinancialManager(storageService);
        ConsoleMenu menu = new ConsoleMenu(manager);
        menu.run();
    }
}