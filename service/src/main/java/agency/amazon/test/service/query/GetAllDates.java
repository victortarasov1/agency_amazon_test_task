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
public class GetAllDates implements Query {

    private final SalesAndTrafficRepository repository;
    @Override
    public List<SalesAndTraffic> execute(List<String> params) {
        return repository.findAll().stream().filter(this::isDateReport).toList();
    }

    @Override
    public String getType() {
        return "GET_SALES_AND_TRAFFIC_BY_ALL_DATES";
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
