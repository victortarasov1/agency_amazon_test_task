package agency.amazon.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sales {
    private AmountAndCurrency orderedProductSales;
    private AmountAndCurrency orderedProductSalesB2B;
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
    private AmountAndCurrency averageSalesPerOrderItem;
    private AmountAndCurrency averageSalesPerOrderItemB2B;
    private BigDecimal averageUnitsPerOrderItem;
    private BigDecimal averageUnitsPerOrderItemB2B;
    private AmountAndCurrency averageSellingPrice;
    private AmountAndCurrency averageSellingPriceB2B;
    private Integer unitsRefunded;
    private BigDecimal refundRate;
    private Integer claimsGranted;
    private AmountAndCurrency claimsAmount;
    private AmountAndCurrency shippedProductSales;
    private Integer unitsShipped;
    private Integer ordersShipped;
}
