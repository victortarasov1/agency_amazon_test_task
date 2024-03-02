package agency.amazon.test.batch.dto;

import agency.amazon.test.model.SalesAndTrafficByAsin;
import agency.amazon.test.model.SalesAndTrafficByDate;
import lombok.Data;

import java.util.List;

@Data
public class SalesAndTrafficReport {
    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
