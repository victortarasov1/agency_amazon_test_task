package agency.amazon.test.service;

import agency.amazon.test.dto.ReportQuery;
import agency.amazon.test.exception.UnknownQueryTypeException;
import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.service.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SalesAndTrafficQueryHandlerImpl implements SalesAndTrafficQueryHandler {
    private final Map<String, Query> queries;

    public SalesAndTrafficQueryHandlerImpl(List<Query> queries) {
        this.queries = queries.stream().collect(Collectors.toMap(Query::getType, Function.identity()));
    }

    @Override
    public List<SalesAndTraffic> executeQuery(ReportQuery reportQuery) {
        var query = queries.get(reportQuery.type());
        if(query == null) throw new UnknownQueryTypeException(reportQuery.type());
        return query.execute(reportQuery.data());
    }
}
