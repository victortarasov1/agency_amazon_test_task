package agency.amazon.test.service.report;

import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;

public interface SalesAndTrafficService {
    List<SalesAndTraffic> findById(String id);
    List<SalesAndTraffic> findAllById(List<String> ids);
    List<SalesAndTraffic> findAll();
    String getIdType();
}
