package agency.amazon.test.service.report;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficService {
    private final SalesAndTrafficRepository repository;
    @Override
    public SalesAndTraffic findById(String asin) {
        return repository.findById(asin).orElseGet(SalesAndTraffic::new);
    }

    @Override
    public List<SalesAndTraffic> findAllById(List<String> asin) {
        return repository.findAllById(asin);
    }

    @Override
    public List<SalesAndTraffic> findAll() {
        return repository.findAll().stream().filter(this::isAsinReport).toList();
    }

    @Override
    public String getIdType() {
        return "Asin";
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
