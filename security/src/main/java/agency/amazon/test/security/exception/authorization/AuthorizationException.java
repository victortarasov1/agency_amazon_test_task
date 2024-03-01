package agency.amazon.test.security.exception.authorization;

public class AuthorizationException extends SecurityException {
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
