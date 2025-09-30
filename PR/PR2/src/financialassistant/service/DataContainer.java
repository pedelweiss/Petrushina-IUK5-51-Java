package src.financialassistant.service;

import src.financialassistant.model.ScheduledPayment;
import src.financialassistant.model.Transaction;
import java.util.List;

public class DataContainer {
    private final List<Transaction> transactions;
    private final List<ScheduledPayment> scheduledPayments;

    public DataContainer(List<Transaction> transactions, List<ScheduledPayment> payments) {
        this.transactions = transactions;
        this.scheduledPayments = payments;
    }

    public List<Transaction> getTransactions() { return transactions; }
    public List<ScheduledPayment> getScheduledPayments() { return scheduledPayments; }
}