package src.financialassistant.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public abstract class Transaction {

    private final String id;
    private BigDecimal amount;
    private LocalDate date;
    private String description;

    public Transaction(BigDecimal amount, LocalDate date, String description) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("Дата: %s, Сумма: %.2f, Описание: '%s'", date, amount, description);
    }
}