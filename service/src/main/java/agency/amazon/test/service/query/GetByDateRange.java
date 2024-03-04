package agency.amazon.test.service.query;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
@RequiredArgsConstructor
public class GetByDateRange implements Query {
    private final SalesAndTrafficRepository repository;
    @Override
    public List<SalesAndTraffic> execute(List<String> params) {
        var startDate = LocalDate.parse(params.get(0));
        var endDate = LocalDate.parse(params.get(params.size() -1));
        var dates = startDate.datesUntil(endDate.plusDays(1)).map(LocalDate::toString).toList();
        return repository.findAllById(dates);
    }

    @Override
    public String getType() {
        return "GET_SALES_AND_TRAFFIC_BY_DATE_RANGE";
    }
}
