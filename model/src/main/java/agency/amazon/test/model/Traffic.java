package agency.amazon.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Traffic {
    private Integer browserSessions;
    private Integer browserSessionsB2B;
    private Integer mobileAppSessions;
    private Integer mobileAppSessionsB2B;
    private Integer sessions;
    private Integer sessionsB2B;
    private BigDecimal browserSessionPercentage;
    private BigDecimal browserSessionPercentageB2B;
    private BigDecimal mobileAppSessionPercentage;
    private BigDecimal mobileAppSessionPercentageB2B;
    private BigDecimal sessionPercentage;
    private BigDecimal sessionPercentageB2B;
    private Integer browserPageViews;
    private Integer browserPageViewsB2B;
    private Integer mobileAppPageViews;
    private Integer mobileAppPageViewsB2B;
    private Integer pageViews;
    private Integer pageViewsB2B;
    private BigDecimal browserPageViewsPercentage;
    private BigDecimal browserPageViewsPercentageB2B;
    private BigDecimal mobileAppPageViewsPercentage;
    private BigDecimal mobileAppPageViewsPercentageB2B;
    private BigDecimal pageViewsPercentage;
    private BigDecimal pageViewsPercentageB2B;
    private BigDecimal buyBoxPercentage;
    private BigDecimal buyBoxPercentageB2B;
    private BigDecimal unitSessionPercentage;
    private BigDecimal unitSessionPercentageB2B;
    private BigDecimal orderItemSessionPercentage;
    private BigDecimal orderItemSessionPercentageB2B;
    private Integer averageOfferCount;
    private Integer averageParentItems;
    private Integer feedbackReceived;
    private Integer negativeFeedbackReceived;
    private Integer receivedNegativeFeedbackRate;
}
