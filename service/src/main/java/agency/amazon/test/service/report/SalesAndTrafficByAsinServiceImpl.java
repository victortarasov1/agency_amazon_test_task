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
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficService {
    private final SalesAndTrafficRepository repository;
    @Override
    @Cacheable(value = "salesAndTraffic", key = "#asin")
    public SalesAndTraffic findById(String asin) {
        return repository.findById(asin).orElseGet(SalesAndTraffic::new);
    }

    @Override
    @Cacheable(value = "salesAndTraffic", key = "#asins")
    public List<SalesAndTraffic> findAllById(List<String> asins) {
        return repository.findAllById(asins);
    }

    @Override
    @Cacheable(value = "salesAndTraffic", key = "'findAllAsin'")
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
