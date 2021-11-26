package com.treinamento.EcommerceBackend.resources.exceptions;
import com.treinamento.EcommerceBackend.services.exceptions.DataIntegrityException;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error1 = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error1);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request){
        String error = "Data Delete Problem";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error1 = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error1);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationException(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError error = new ValidationError(Instant.now(), status.value(), "Validation error!");
        for(FieldError validationError : e.getBindingResult().getFieldErrors()){
            error.addError(validationError.getField(), validationError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }
    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<StandardError> validationException(AuthorizationServiceException e, HttpServletRequest request){
        StandardError error = new StandardError(Instant.now(), HttpStatus.FORBIDDEN.value(), "You can't access this data!");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }




}
