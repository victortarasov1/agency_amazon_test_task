package agency.amazon.test.security.service;

public interface AuthorizationService {

    void authorizeIfTokenIsValid(String accessToken);

}
