package agency.amazon.test.batch.dto;

import agency.amazon.test.model.SalesAndTraffic;
import lombok.Data;

import java.util.List;

@Data
public class SalesAndTrafficReport {
    private ReportSpecification reportSpecification;
    private List<SalesAndTraffic> salesAndTrafficByDate;
    private List<SalesAndTraffic> salesAndTrafficByAsin;
}
