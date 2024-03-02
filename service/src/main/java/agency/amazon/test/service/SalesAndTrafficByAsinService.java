package agency.amazon.test.service;

import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;

public interface SalesAndTrafficByAsinService {
    List<SalesAndTraffic> findByAsin(String asin);

    List<SalesAndTraffic> findAsinList(List<String> asin);

    List<SalesAndTraffic> findAll();
}
