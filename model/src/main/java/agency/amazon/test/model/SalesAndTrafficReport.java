package agency.amazon.test.model;

import lombok.Data;

@Data
public class SalesAndTrafficReport {
    private ReportSpecification reportSpecification;
    private SalesAndTrafficByDate salesAndTrafficByDate;
    private SalesAndTrafficByAsin salesAndTrafficByAsin;
}
