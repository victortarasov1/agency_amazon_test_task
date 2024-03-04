package agency.amazon.test.service;

import agency.amazon.test.dto.ReportQuery;
import agency.amazon.test.model.SalesAndTraffic;

import java.util.List;

public interface SalesAndTrafficFacade {

    SalesAndTraffic findById(ReportQuery dto);
    List<SalesAndTraffic> findAllById(ReportQuery dto);
    List<SalesAndTraffic> findAll(ReportQuery dto);
}
