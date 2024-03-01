package agency.amazon.test.security.exception.authentication;

public class AuthenticationException extends SecurityException {
    public AuthenticationException(String message) {
        super(message);
    }
}
