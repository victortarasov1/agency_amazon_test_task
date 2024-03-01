package agency.amazon.test.model;

import lombok.Data;

@Data
public class TrafficByDate {
    private int browserPageViews;
    private int browserPageViewsB2B;
    private int mobileAppPageViews;
    private int mobileAppPageViewsB2B;
    private int pageViews;
    private int pageViewsB2B;
    private int browserSessions;
    private int browserSessionsB2B;
    private int mobileAppSessions;
    private int mobileAppSessionsB2B;
    private int sessions;
    private int sessionsB2B;
    private int buyBoxPercentage;
    private int buyBoxPercentageB2B;
    private int orderItemSessionPercentage;
    private int orderItemSessionPercentageB2B;
    private int unitSessionPercentage;
    private int unitSessionPercentageB2B;
    private int averageOfferCount;
    private int averageParentItems;
    private int feedbackReceived;
    private int negativeFeedbackReceived;
    private int receivedNegativeFeedbackRate;

}
