package agency.amazon.test.service;

import agency.amazon.test.dto.StatisticsReportDto;
import agency.amazon.test.model.SalesAndTrafficByDate;
import agency.amazon.test.repository.SalesAndTrafficByDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {
    private final SalesAndTrafficByDateRepository repository;

    @Override
    public StatisticsReportDto findByDate(LocalDate date) {
        var salesAndTraffic = repository.findByDate(date).orElseGet(SalesAndTrafficByDate::new);
        var dto = new StatisticsReportDto();
        dto.setStatisticsByDate(List.of(salesAndTraffic));
        return dto;
    }

    @Override
    public StatisticsReportDto findByDateRange(LocalDate startDate, LocalDate endDate) {
        var salesAndTraffics = repository.findByDateBetween(startDate, endDate);
        var dto = new StatisticsReportDto();
        dto.setStatisticsByDate(salesAndTraffics);
        return dto;
    }

    @Override
    public StatisticsReportDto findAll() {
        var salesAndTraffics = repository.findAll();
        var dto = new StatisticsReportDto();
        dto.setStatisticsByDate(salesAndTraffics);
        return dto;
    }


}
