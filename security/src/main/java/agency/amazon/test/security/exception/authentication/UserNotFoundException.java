package agency.amazon.test.security.exception.authentication;

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String email) {
        super("user with email " + email + " not found");
    }
}
