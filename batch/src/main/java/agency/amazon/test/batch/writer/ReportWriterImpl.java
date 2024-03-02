package agency.amazon.test.batch.writer;

import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportWriterImpl implements ReportWriter {
    private final SalesAndTrafficRepository salesAndTrafficRepository;
    @Override
    @CacheEvict(value = "salesAndTraffic", allEntries = true)
    public void saveToDb(SalesAndTrafficReport report) {
        salesAndTrafficRepository.saveAll(report.getSalesAndTrafficByDate());
        salesAndTrafficRepository.saveAll(report.getSalesAndTrafficByAsin());
    }
}
