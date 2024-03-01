package agency.amazon.test.security.exception.authentication;

public class BadPasswordOrEmailException extends AuthenticationException {
    public BadPasswordOrEmailException(String email) {
        super("bad password and/or email! " + email);
    }
}
