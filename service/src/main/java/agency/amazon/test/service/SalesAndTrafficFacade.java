package agency.amazon.test.service;

import agency.amazon.test.dto.StatisticsReportDto;
import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;

public interface SalesAndTrafficFacade {

    List<SalesAndTraffic> findById(StatisticsReportDto dto);
    List<SalesAndTraffic> findAllById(StatisticsReportDto dto);
    List<SalesAndTraffic> findAll(StatisticsReportDto dto);
}
