package agency.amazon.test.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private LocalDate dataStartTime;

    private List<String> marketplaceIds;
}
