package agency.amazon.test.security.exception.authorization;

public class BadTokenException extends AuthorizationException{
    public BadTokenException(Throwable cause) {
        super("authorization failed", cause);
    }
}
