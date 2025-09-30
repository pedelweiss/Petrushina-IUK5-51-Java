package src.financialassistant.service;

import src.financialassistant.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonStorageService implements DataStorageService {

    private static final String FILE_NAME = "financial_data.json";

    public JsonStorageService() {
    }

    @Override
    public void saveData(DataContainer data) {
        JSONObject rootJson = new JSONObject();

        JSONArray transactionsArray = new JSONArray();
        for (Transaction tx : data.getTransactions()) {
            transactionsArray.put(transactionToJson(tx));
        }
        rootJson.put("transactions", transactionsArray);

        JSONArray paymentsArray = new JSONArray();
        for (ScheduledPayment payment : data.getScheduledPayments()) {
            paymentsArray.put(paymentToJson(payment));
        }
        rootJson.put("scheduledPayments", paymentsArray);

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(rootJson.toString(4));
        } catch (IOException e) {
            System.err.println("Ошибка сохранения в JSON файл: " + e.getMessage());
        }
    }

    @Override
    public DataContainer loadData() {
        if (!Files.exists(Paths.get(FILE_NAME))) {
            return new DataContainer(new ArrayList<>(), new ArrayList<>());
        }

        try {
            String content = Files.readString(Paths.get(FILE_NAME));
            if (content.isEmpty()) {
                return new DataContainer(new ArrayList<>(), new ArrayList<>());
            }

            JSONObject rootJson = new JSONObject(content);

            List<Transaction> transactions = new ArrayList<>();
            JSONArray transactionsArray = rootJson.getJSONArray("transactions");
            for (int i = 0; i < transactionsArray.length(); i++) {
                JSONObject txJson = transactionsArray.getJSONObject(i);
                transactions.add(jsonToTransaction(txJson));
            }

            List<ScheduledPayment> scheduledPayments = new ArrayList<>();
            JSONArray paymentsArray = rootJson.getJSONArray("scheduledPayments");
            for (int i = 0; i < paymentsArray.length(); i++) {
                JSONObject pJson = paymentsArray.getJSONObject(i);
                scheduledPayments.add(jsonToPayment(pJson));
            }

            return new DataContainer(transactions, scheduledPayments);

        } catch (Exception e) { // Ловим IOException и JSONException
            System.err.println("Ошибка чтения или парсинга JSON файла: " + e.getMessage());
            return new DataContainer(new ArrayList<>(), new ArrayList<>());
        }
    }


    private JSONObject transactionToJson(Transaction tx) {
        JSONObject json = new JSONObject();
        json.put("type", tx instanceof Income ? "INCOME" : "EXPENSE");
        json.put("amount", tx.getAmount());
        json.put("date", tx.getDate().toString()); // Сохраняем дату как строку
        json.put("description", tx.getDescription());
        return json;
    }

    private JSONObject paymentToJson(ScheduledPayment payment) {
        JSONObject json = new JSONObject();
        json.put("paymentDate", payment.getPaymentDate().toString());
        json.put("amount", payment.getAmount());
        json.put("description", payment.getDescription());
        json.put("isPaid", payment.isPaid());
        return json;
    }

    private Transaction jsonToTransaction(JSONObject json) {
        String type = json.getString("type");
        BigDecimal amount = json.getBigDecimal("amount");
        LocalDate date = LocalDate.parse(json.getString("date")); // Парсим дату из строки
        String description = json.getString("description");

        if ("INCOME".equals(type)) {
            return new Income(amount, date, description);
        } else {
            return new Expense(amount, date, description);
        }
    }

    private ScheduledPayment jsonToPayment(JSONObject json) {
        LocalDate paymentDate = LocalDate.parse(json.getString("paymentDate"));
        BigDecimal amount = json.getBigDecimal("amount");
        String description = json.getString("description");
        boolean isPaid = json.getBoolean("isPaid");

        ScheduledPayment payment = new ScheduledPayment(paymentDate, amount, description);
        payment.setPaid(isPaid);
        return payment;
    }
}