package agency.amazon.test.model;

import lombok.Data;

import java.util.List;

@Data
public class SalesAndTrafficReport {
    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
