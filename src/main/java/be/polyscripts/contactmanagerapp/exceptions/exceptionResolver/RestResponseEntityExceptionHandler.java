package be.polyscripts.contactmanagerapp.exceptions.exceptionResolver;

import be.polyscripts.contactmanagerapp.exceptions.CompanyNotFoundException;
import be.polyscripts.contactmanagerapp.exceptions.ContactNotFoundException;
import be.polyscripts.contactmanagerapp.exceptions.FreelanceWithNoTVAException;
import be.polyscripts.contactmanagerapp.exceptions.UserNotFoundException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@CommonsLog
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FreelanceWithNoTVAException.class)
    protected ResponseEntity<Object> handleConflict(FreelanceWithNoTVAException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(CompanyNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(ContactNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(UserNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}