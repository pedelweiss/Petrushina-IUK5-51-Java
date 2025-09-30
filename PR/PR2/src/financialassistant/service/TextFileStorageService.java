package src.financialassistant.service;

import src.financialassistant.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TextFileStorageService implements DataStorageService {
    private static final String FILE_NAME = "financial_data.txt";
    private static final String DELIMITER = ";";

    @Override
    public void saveData(DataContainer data) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Transaction tx : data.getTransactions()) {
                String type = tx instanceof Income ? "INCOME" : "EXPENSE";
                String line = String.join(DELIMITER,
                        "TRANSACTION",
                        type,
                        tx.getAmount().toString(),
                        tx.getDate().toString(),
                        tx.getDescription()
                );
                writer.write(line + System.lineSeparator());
            }

            for (ScheduledPayment payment : data.getScheduledPayments()) {
                String line = String.join(DELIMITER,
                        "PAYMENT",
                        payment.getPaymentDate().toString(),
                        payment.getAmount().toString(),
                        payment.getDescription(),
                        String.valueOf(payment.isPaid())
                );
                writer.write(line + System.lineSeparator());
            }

        } catch (IOException e) {
            System.err.println("Ошибка сохранения в файл: " + e.getMessage());
        }
    }

    @Override
    public DataContainer loadData() {

        if (!Files.exists(Paths.get(FILE_NAME))) {
            return new DataContainer(new ArrayList<>(), new ArrayList<>());
        }

        List<Transaction> transactions = new ArrayList<>();
        List<ScheduledPayment> scheduledPayments = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length < 1) continue; // Пропускаем пустые строки

                String recordType = parts[0];
                try {
                    if ("TRANSACTION".equals(recordType) && parts.length == 5) {
                        String type = parts[1];
                        BigDecimal amount = new BigDecimal(parts[2]);
                        LocalDate date = LocalDate.parse(parts[3]);
                        String description = parts[4];

                        if ("INCOME".equals(type)) {
                            transactions.add(new Income(amount, date, description));
                        } else {
                            transactions.add(new Expense(amount, date, description));
                        }

                    } else if ("PAYMENT".equals(recordType) && parts.length == 5) {
                        LocalDate paymentDate = LocalDate.parse(parts[1]);
                        BigDecimal amount = new BigDecimal(parts[2]);
                        String description = parts[3];
                        boolean isPaid = Boolean.parseBoolean(parts[4]);

                        ScheduledPayment payment = new ScheduledPayment(paymentDate, amount, description);
                        payment.setPaid(isPaid);
                        scheduledPayments.add(payment);
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка парсинга строки: '" + line + "'. Пропускаем. Ошибка: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения из файла: " + e.getMessage());
        }

        return new DataContainer(transactions, scheduledPayments);
    }
}