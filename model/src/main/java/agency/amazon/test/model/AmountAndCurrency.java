package agency.amazon.test.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AmountAndCurrency {
    private BigDecimal amount;
    private String currencyCode;
}
