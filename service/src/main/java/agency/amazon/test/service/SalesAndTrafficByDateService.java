package agency.amazon.test.service;

import agency.amazon.test.model.SalesAndTraffic;

import java.time.LocalDate;
import java.util.List;

public interface SalesAndTrafficByDateService {
    List<SalesAndTraffic> findByDate(LocalDate date);

    List<SalesAndTraffic> findByDateRange(LocalDate startDate, LocalDate endDate);

    List<SalesAndTraffic> findAll();


}
