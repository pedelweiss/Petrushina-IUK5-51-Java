package src.financialassistant.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(BigDecimal amount, LocalDate date, String description) {
        super(amount, date, description);
    }

    @Override
    public String getType() {
        return "РАСХОД";
    }

    @Override
    public String toString() {
        return "[РАСХОД] " + super.toString();
    }
}