package agency.amazon.test.service.query;

import agency.amazon.test.model.SalesAndTraffic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetByDate implements Query {

    private final Query query;

    public GetByDate(@Qualifier("by_list") Query query) {
        this.query = query;
    }

    @Override
    public List<SalesAndTraffic> execute(List<String> params) {
        return query.execute(params);
    }

    @Override
    public String getType() {
        return "GET_SALES_AND_TRAFFIC_BY_DATE";
    }
}
