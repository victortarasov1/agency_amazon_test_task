package agency.amazon.test.service.query;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
@Qualifier("by_list")
public class GetByAsinList implements Query {
    private final SalesAndTrafficRepository repository;
    @Override
    public List<SalesAndTraffic> execute(List<String> params) {
        return repository.findAllById(params);
    }

    @Override
    public String getType() {
        return "GET_SALES_AND_TRAFFIC_BY_DATE_OR_ASIN_LIST";
    }
}
