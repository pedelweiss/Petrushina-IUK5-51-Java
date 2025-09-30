package src.financialassistant.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ScheduledPayment {
    private final String id;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String description;
    private boolean isPaid;

    public ScheduledPayment(LocalDate paymentDate, BigDecimal amount, String description) {
        this.id = UUID.randomUUID().toString();
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.description = description;
        this.isPaid = false;
    }

    public String getId() { return id; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }

    @Override
    public String toString() {
        String status = isPaid ? "Оплачено" : "Ожидает оплаты";
        return String.format("Дата платежа: %s, Сумма: %.2f, Описание: '%s', Статус: %s",
                paymentDate, amount, description, status);
    }
}
