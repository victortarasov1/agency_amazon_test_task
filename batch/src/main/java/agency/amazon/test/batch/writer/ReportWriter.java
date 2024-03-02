package agency.amazon.test.batch.writer;

import agency.amazon.test.batch.dto.SalesAndTrafficReport;

public interface ReportWriter {
    void saveToDb(SalesAndTrafficReport report);
}
