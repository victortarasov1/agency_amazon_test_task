package agency.amazon.test.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message){
        super(message);
    }
}
