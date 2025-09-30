package src.financialassistant.manager;

import src.financialassistant.model.*;
import src.financialassistant.service.DataContainer;
import src.financialassistant.service.DataStorageService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialManager {
    private List<Transaction> transactions;
    private List<ScheduledPayment> scheduledPayments;
    private final DataStorageService storageService;

    public FinancialManager(DataStorageService storageService) {
        this.storageService = storageService;
        loadData();
    }

    public void addIncome(BigDecimal amount, LocalDate date, String description) {
        transactions.add(new Income(amount, date, description));
    }

    public void addExpense(BigDecimal amount, LocalDate date, String description) {
        transactions.add(new Expense(amount, date, description));
    }

    public void addScheduledPayment(LocalDate date, BigDecimal amount, String description) {
        scheduledPayments.add(new ScheduledPayment(date, amount, description));
    }

    public BigDecimal getCurrentBalance() {
        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t instanceof Income)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t instanceof Expense)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalIncome.subtract(totalExpense);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<ScheduledPayment> getScheduledPayments() {
        return scheduledPayments;
    }


    public void saveData() {
        storageService.saveData(new DataContainer(transactions, scheduledPayments));
    }

    private void loadData() {
        DataContainer data = storageService.loadData();
        // Создаем изменяемые копии списков
        this.transactions = new ArrayList<>(data.getTransactions());
        this.scheduledPayments = new ArrayList<>(data.getScheduledPayments());
    }
}