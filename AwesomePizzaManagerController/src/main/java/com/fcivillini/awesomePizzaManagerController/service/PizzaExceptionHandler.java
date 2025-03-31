package com.fcivillini.awesomePizzaManagerController.service;

import com.fcivillini.awesomePizzaManagerInterface.exc.PizzaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class PizzaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PizzaException.class})
    public ResponseEntity<String> handleFididocException(PizzaException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
    }

}

