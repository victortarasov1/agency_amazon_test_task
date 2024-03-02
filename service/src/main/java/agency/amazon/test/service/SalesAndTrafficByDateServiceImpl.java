package agency.amazon.test.service;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {

    private final SalesAndTrafficRepository repository;

    @Override
    public List<SalesAndTraffic> findByDate(LocalDate date){
        return repository.findById(date.toString()).map(List::of).orElseGet(List::of);
    }

    @Override
    public List<SalesAndTraffic> findByDateRange(LocalDate startDate, LocalDate endDate) {
        var dates = startDate.datesUntil(endDate.plusDays(1)).map(LocalDate::toString).toList();
        return repository.findAllById(dates);
    }

    @Override
    public List<SalesAndTraffic> findAll() {
        return repository.findAll().stream().filter(this::isDateReport).toList();
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
