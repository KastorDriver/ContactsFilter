package one.kastordriver.contactfilter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.PatternSyntaxException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(PatternSyntaxException.class)
    public ResponseEntity handle() {
        return ResponseEntity.badRequest().body("incorrect regex in 'filterName' parameter");
    }
}
