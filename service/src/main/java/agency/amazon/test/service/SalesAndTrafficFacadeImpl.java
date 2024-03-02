package agency.amazon.test.service;

import agency.amazon.test.dto.StatisticsReportDto;
import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.exception.UnknownIdTypeException;
import agency.amazon.test.service.report.SalesAndTrafficService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SalesAndTrafficFacadeImpl implements SalesAndTrafficFacade {
    public final Map<String, SalesAndTrafficService> services;
    public SalesAndTrafficFacadeImpl(List<SalesAndTrafficService> services) {
        this.services = services.stream().collect(Collectors.toMap(SalesAndTrafficService::getIdType, Function.identity()));
    }

    @Override
    public SalesAndTraffic findById(StatisticsReportDto dto) {
        var service = getService(dto);
        return service.findById(dto.data().get(0));
    }

    @Override
    public List<SalesAndTraffic> findAllById(StatisticsReportDto dto) {
        var service = getService(dto);
        return service.findAllById(dto.data());
    }

    @Override
    public List<SalesAndTraffic> findAll(StatisticsReportDto dto) {
        var service = getService(dto);
        return service.findAll();
    }

    private SalesAndTrafficService getService(StatisticsReportDto dto) {
        var service = services.get(dto.type());
        if(service == null) throw new UnknownIdTypeException(dto.type());
        return service;
    }
}