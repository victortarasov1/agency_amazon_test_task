package agency.amazon.test.service.query;

import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;

public interface Query {
    List<SalesAndTraffic> execute(List<String> params);
    String getType();
}
