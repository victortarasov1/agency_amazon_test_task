package agency.amazon.test.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class SalesAndTrafficByDate {
    @Id
    private String id;
    private LocalDate date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
