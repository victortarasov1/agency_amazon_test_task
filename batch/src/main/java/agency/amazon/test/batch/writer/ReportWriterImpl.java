package agency.amazon.test.batch.writer;

import agency.amazon.test.model.SalesAndTrafficByDate;
import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import agency.amazon.test.repository.SalesAndTrafficByAsinRepository;
import agency.amazon.test.repository.SalesAndTrafficByDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportWriterImpl implements ReportWriter {
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;
    @Override
    public void saveToDb(SalesAndTrafficReport report) {
        var salesAndTrafficByAsins = report.getSalesAndTrafficByAsin();
        salesAndTrafficByAsinRepository.saveAll(salesAndTrafficByAsins);

        var salesAndTrafficByDates = report.getSalesAndTrafficByDate();
        handleExisting(salesAndTrafficByDates);
    }

    private void handleExisting(List<SalesAndTrafficByDate> salesAndTrafficByDates) {
        for(var item : salesAndTrafficByDates) {
            var exist = salesAndTrafficByDateRepository.findByDate(item.getDate());
            if(exist.isPresent()) update(exist.get(), item);
            else salesAndTrafficByDateRepository.save(item);
        }
    }

    private void update(SalesAndTrafficByDate existOne, SalesAndTrafficByDate newOne) {
        existOne.setSalesByDate(newOne.getSalesByDate());
        existOne.setTrafficByDate(newOne.getTrafficByDate());
        salesAndTrafficByDateRepository.save(existOne);
    }
}
