package agency.amazon.test.service;

import agency.amazon.test.dto.StatisticsReportDto;

import java.time.LocalDate;

public interface SalesAndTrafficByDateService {
    StatisticsReportDto findByDate(LocalDate date);

    StatisticsReportDto findByDateRange(LocalDate startDate, LocalDate endDate);

    StatisticsReportDto findAll();


}
