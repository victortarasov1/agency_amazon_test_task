package agency.amazon.test.repository;

import agency.amazon.test.model.SalesAndTrafficByDate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, LocalDate> {
}
