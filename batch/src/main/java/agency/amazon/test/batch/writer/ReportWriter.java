package agency.amazon.test.batch.writer;

import agency.amazon.test.model.SalesAndTrafficReport;

public interface ReportWriter {
    void saveToDb(SalesAndTrafficReport report);
}
