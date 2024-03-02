package agency.amazon.test.batch.exception;

public class UnknownFileTypeException extends BatchProcessingException {
    public UnknownFileTypeException(String fileType) {
        super("Unknown type of file: " + fileType);
    }
}
