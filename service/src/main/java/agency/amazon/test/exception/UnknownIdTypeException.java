package agency.amazon.test.exception;

public class UnknownIdTypeException extends ServiceException {
    public UnknownIdTypeException(String type) {
        super("unknown type of id: " + type);
    }
}
