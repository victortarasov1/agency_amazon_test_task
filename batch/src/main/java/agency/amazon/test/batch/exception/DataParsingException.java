package agency.amazon.test.batch.exception;

public class DataParsingException extends BatchProcessingException {
    public DataParsingException(Throwable cause) {
        super("data parsion exception", cause);
    }
}
