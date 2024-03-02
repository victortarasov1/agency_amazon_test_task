package agency.amazon.test.batch.writer;

import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ReportWriterImplTest {
    private SalesAndTrafficRepository repository;

    private ReportWriter writer;

    @BeforeEach
    public void setUp() {
        repository = mock(SalesAndTrafficRepository.class);
        writer = new ReportWriterImpl(repository);
    }

    @Test
    public void testSaveToDb(){
        var report = new SalesAndTrafficReport();
        report.setSalesAndTrafficByAsin(List.of());
        report.setSalesAndTrafficByDate(List.of());
        writer.saveToDb(report);
        verify(repository, times(2)).saveAll(anyList());
    }

}