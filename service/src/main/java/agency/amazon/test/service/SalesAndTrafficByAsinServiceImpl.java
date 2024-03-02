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
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficByAsinService {
    private final SalesAndTrafficRepository repository;
    @Override
    public List<SalesAndTraffic> findByAsin(String asin) {
        return repository.findById(asin).map(List::of).orElseGet(List::of);
    }

    @Override
    public List<SalesAndTraffic> findAsinList(List<String> asin) {
        return repository.findAllById(asin);
    }

    @Override
    public List<SalesAndTraffic> findAll() {
        return repository.findAll().stream().filter(this::isAsinReport).toList();
    }

    private boolean isAsinReport(SalesAndTraffic salesAndTraffic) {
        try {
            LocalDate.parse(salesAndTraffic.getId());
            return false;
        } catch (DateTimeParseException ex) {
            return true;
        }
    }
}
