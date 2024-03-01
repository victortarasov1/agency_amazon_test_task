package agency.amazon.test.batch.exception;

public class BatchProcessingException extends RuntimeException {

    public BatchProcessingException(String message, Throwable cause){
        super(message, cause);
    }

    public BatchProcessingException(String message){
        super(message);
    }
}
