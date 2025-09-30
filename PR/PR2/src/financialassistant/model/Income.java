package src.financialassistant.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income extends Transaction {
    public Income(BigDecimal amount, LocalDate date, String description) {
        super(amount, date, description);
    }

    @Override
    public String getType() {
        return "ДОХОД";
    }

    @Override
    public String toString() {
        return "[ДОХОД] " + super.toString();
    }
}
