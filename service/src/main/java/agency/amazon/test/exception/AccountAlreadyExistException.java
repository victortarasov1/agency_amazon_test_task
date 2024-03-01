package agency.amazon.test.exception;

public class AccountAlreadyExistException extends ServiceException {
    public AccountAlreadyExistException(String email) {
        super("account " + email + "already exist");
    }
}
