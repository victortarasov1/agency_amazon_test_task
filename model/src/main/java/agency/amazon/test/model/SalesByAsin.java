package agency.amazon.test.model;

import lombok.Data;

@Data
public class SalesByAsin {
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private AmountAndCurrency orderedProductSales;
    private AmountAndCurrency orderedProductSalesB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
}
