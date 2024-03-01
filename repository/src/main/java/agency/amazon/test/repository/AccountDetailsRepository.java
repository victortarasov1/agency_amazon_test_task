package agency.amazon.test.repository;


import agency.amazon.test.model.AccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountDetailsRepository extends MongoRepository<AccountDetails, String> {
    Optional<AccountDetails> findByEmail(String email);
}
