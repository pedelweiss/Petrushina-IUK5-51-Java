package src.financialassistant.ui;

import src.financialassistant.manager.FinancialManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenu {
    private final FinancialManager manager;
    private final Scanner scanner;

    public ConsoleMenu(FinancialManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n===== Финансовый помощник =====");
        System.out.println("1. Показать текущий баланс");
        System.out.println("2. Добавить доход");
        System.out.println("3. Добавить расход");
        System.out.println("4. Показать все транзакции");
        System.out.println("5. Показать календарь платежей");
        System.out.println("6. Добавить запланированный платеж");
        System.out.println("---------------------------------");
        System.out.println("0. Сохранить и выйти");
        System.out.println("===============================");
    }

    /**
     * Запускает главный цикл приложения.
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Выберите опцию: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // съедаем перенос строки после nextInt()

                switch (choice) {
                    case 1:
                        showBalance();
                        break;
                    case 2:
                        addTransaction(true); // true для дохода
                        break;
                    case 3:
                        addTransaction(false); // false для расхода
                        break;
                    case 4:
                        viewAllTransactions();
                        break;
                    case 5:
                        viewScheduledPayments();
                        break;
                    case 6:
                        addScheduledPayment();
                        break;
                    case 0:
                        manager.saveData();
                        exit = true;
                        System.out.println("Данные сохранены. До свидания!");
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Ошибка: Введите число, соответствующее пункту меню.");
                scanner.nextLine(); // Очищаем буфер сканера от неверного ввода
            }
        }
        scanner.close(); // Закрываем сканер при выходе
    }

    private void showBalance() {
        System.out.printf("\n>>> Текущий баланс: %.2f\n", manager.getCurrentBalance());
    }

    private void viewAllTransactions() {
        System.out.println("\n--- Все транзакции ---");
        var transactions = manager.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("Список транзакций пуст.");
        } else {
            transactions.forEach(System.out::println);
        }
        System.out.println("----------------------");
    }

    private void viewScheduledPayments() {
        System.out.println("\n--- Календарь платежей ---");
        var payments = manager.getScheduledPayments();
        if (payments.isEmpty()) {
            System.out.println("Запланированных платежей нет.");
        } else {
            payments.forEach(System.out::println);
        }
        System.out.println("--------------------------");
    }

    private void addTransaction(boolean isIncome) {
        try {
            String type = isIncome ? "дохода" : "расхода";
            System.out.printf("\n--- Добавление %s ---\n", type);
            System.out.print("Введите сумму: ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); // съедаем перенос строки

            System.out.print("Введите описание: ");
            String description = scanner.nextLine();

            if (isIncome) {
                manager.addIncome(amount, LocalDate.now(), description);
                System.out.println(">>> Доход успешно добавлен.");
            } else {
                manager.addExpense(amount, LocalDate.now(), description);
                System.out.println(">>> Расход успешно добавлен.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Ошибка: Сумма должна быть числом. Попробуйте снова.");
            scanner.nextLine(); // Очищаем буфер
        }
    }

    private void addScheduledPayment() {
        try {
            System.out.println("\n--- Добавление запланированного платежа ---");
            System.out.print("Введите сумму: ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); // съедаем перенос строки

            System.out.print("Введите описание: ");
            String description = scanner.nextLine();

            System.out.print("Введите дату платежа (в формате гггг-мм-дд): ");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString); // Может выбросить DateTimeParseException

            manager.addScheduledPayment(date, amount, description);
            System.out.println(">>> Запланированный платеж успешно добавлен.");

        } catch (InputMismatchException e) {
            System.err.println("Ошибка: Сумма должна быть числом. Попробуйте снова.");
            scanner.nextLine(); // Очищаем буфер
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка: Неверный формат даты. Пожалуйста, используйте гггг-мм-дд.");
        }
    }
}