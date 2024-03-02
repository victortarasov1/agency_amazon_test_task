package agency.amazon.test.repository;

import agency.amazon.test.model.SalesAndTrafficByDate;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, LocalDate> {
    Optional<SalesAndTrafficByDate> findByDate(LocalDate date);

    List<SalesAndTrafficByDate> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
