package agency.amazon.test.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SalesAndTraffic {
    @Id
    @JsonAlias({"date", "parentAsin"})
    private String id;
    private Sales salesByAsin;
    private Traffic trafficByAsin;
}
