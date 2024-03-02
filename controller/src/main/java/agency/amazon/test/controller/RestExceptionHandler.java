package agency.amazon.test.controller;

import agency.amazon.test.exception.ServiceException;
import agency.amazon.test.model.ApiError;
import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ApiError> handleSourceExceptions(ServiceException ex) {
        var debugMessages = Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).stream().toList();
        ApiError apiError = new ApiError(ex.getMessage(), debugMessages);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ApiError apiError = new ApiError("Malformed JSON Request", List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        List<String> debugMessages = Optional.ofNullable(ex.getRequiredType()).map(Class::getSimpleName).stream().toList();
        ApiError apiError = new ApiError("bad argument type",
                List.of(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                        ex.getName(), ex.getValue(), debugMessages)));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        var apiError = new ApiError(ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
