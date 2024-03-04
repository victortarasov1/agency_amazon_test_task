package agency.amazon.test.exception;

public class UnknownQueryTypeException extends ServiceException {
    public UnknownQueryTypeException(String type) {
        super("unknown type of id: " + type);
    }
}
