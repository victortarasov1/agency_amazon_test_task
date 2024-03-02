package agency.amazon.test.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesByDate {
    private AmountAndCurrency orderedProductSales;
    private AmountAndCurrency orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private AmountAndCurrency averageSalesPerOrderItem;
    private AmountAndCurrency averageSalesPerOrderItemB2B;
    private BigDecimal averageUnitsPerOrderItem;
    private BigDecimal averageUnitsPerOrderItemB2B;
    private AmountAndCurrency averageSellingPrice;
    private AmountAndCurrency averageSellingPriceB2B;
    private int unitsRefunded;
    private BigDecimal refundRate;
    private int claimsGranted;
    private AmountAndCurrency claimsAmount;
    private AmountAndCurrency shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;



}
