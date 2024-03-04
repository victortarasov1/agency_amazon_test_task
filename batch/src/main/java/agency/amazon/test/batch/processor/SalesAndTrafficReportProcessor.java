package agency.amazon.test.batch.processor;

import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import agency.amazon.test.model.SalesAndTraffic;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SalesAndTrafficReportProcessor implements ItemProcessor<SalesAndTrafficReport, List<SalesAndTraffic>> {
    @Override
    public List<SalesAndTraffic> process(SalesAndTrafficReport report) {
        var data = report.getSalesAndTrafficByDate();
        data.addAll(report.getSalesAndTrafficByAsin());
        return data;
    }
}
