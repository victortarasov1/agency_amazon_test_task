package agency.amazon.test.repository;


import agency.amazon.test.model.AccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountDetailsRepository extends MongoRepository<AccountDetails, String> {
}
