package agency.amazon.test.dto;

import agency.amazon.test.model.SalesAndTrafficByAsin;
import agency.amazon.test.model.SalesAndTrafficByDate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StatisticsReportDto {
    private List<SalesAndTrafficByDate> statisticsByDate;
    private List<SalesAndTrafficByAsin> statisticsByAsins;
}
