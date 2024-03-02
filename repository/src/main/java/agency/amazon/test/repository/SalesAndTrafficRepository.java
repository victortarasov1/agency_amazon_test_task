package agency.amazon.test.repository;

import agency.amazon.test.model.SalesAndTraffic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesAndTrafficRepository extends MongoRepository<SalesAndTraffic, String> {
}
