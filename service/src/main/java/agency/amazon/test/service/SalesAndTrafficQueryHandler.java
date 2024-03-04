package agency.amazon.test.service;

import agency.amazon.test.dto.ReportQuery;
import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;

public interface SalesAndTrafficQueryHandler {
    List<SalesAndTraffic> executeQuery(ReportQuery reportQuery);
}
