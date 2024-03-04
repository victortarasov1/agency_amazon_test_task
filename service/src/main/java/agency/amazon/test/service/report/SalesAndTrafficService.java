package agency.amazon.test.service.report;

import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;
@Deprecated
public interface SalesAndTrafficService {
    SalesAndTraffic findById(String id);
    List<SalesAndTraffic> findAllById(List<String> ids);
    List<SalesAndTraffic> findAll();
    String getIdType();
}
