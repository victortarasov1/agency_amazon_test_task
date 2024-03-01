package agency.amazon.test.exception;

public class AccountNotFoundException extends ServiceException {
    public AccountNotFoundException(String email) {
        super("account " + email + " not found!");
    }
}
