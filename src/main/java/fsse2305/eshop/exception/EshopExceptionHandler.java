package fsse2305.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EshopExceptionHandler extends ResponseEntityExceptionHandler {
      @ExceptionHandler(value= {Exception.class})
      protected ResponseEntity<Object> handleConflict(Exception e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
      }
}