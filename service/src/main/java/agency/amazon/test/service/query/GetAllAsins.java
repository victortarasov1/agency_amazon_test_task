package agency.amazon.test.service.query;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
@Component
@RequiredArgsConstructor
public class GetAllAsins implements Query {
    private final SalesAndTrafficRepository repository;
    @Override
    public List<SalesAndTraffic> execute(List<String> params) {
        return repository.findAll().stream().filter(this::isASINReport).toList();
    }

    @Override
    public String getType() {
        return "GET_SALES_AND_TRAFFIC_BY_ALL_ASINS";
    }
    private boolean isASINReport(SalesAndTraffic salesAndTraffic) {
        try {
            LocalDate.parse(salesAndTraffic.getId());
            return false;
        } catch (DateTimeParseException ex) {
            return true;
        }
    }
}
