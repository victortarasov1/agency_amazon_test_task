package agency.amazon.test.service.report;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Deprecated
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficService {

    private final SalesAndTrafficRepository repository;

    @Override
    @Cacheable(value = "salesAndTraffic", key = "#date")
    public SalesAndTraffic findById(String date){
        return repository.findById(date).orElseGet(SalesAndTraffic::new);
    }

    @Override
    @Cacheable(value = "salesAndTraffic", key = "#dateRange")
    public List<SalesAndTraffic> findAllById(List<String> dateRange) {
        var startDate = LocalDate.parse(dateRange.get(0));
        var endDate = LocalDate.parse(dateRange.get(dateRange.size() -1));
        var dates = startDate.datesUntil(endDate.plusDays(1)).map(LocalDate::toString).toList();
        return repository.findAllById(dates);
    }

    @Override
    @Cacheable(value = "salesAndTraffic", key = "'findAllData'")
    public List<SalesAndTraffic> findAll() {
        return repository.findAll().stream().filter(this::isDateReport).toList();
    }

    @Override
    public String getIdType() {
        return "Date";
    }

    private boolean isDateReport(SalesAndTraffic salesAndTraffic) {
        try {
            LocalDate.parse(salesAndTraffic.getId());
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}
