package agency.amazon.test.repository;

import agency.amazon.test.model.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {
}
