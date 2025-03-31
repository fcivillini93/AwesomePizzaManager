package com.fcivillini.awesomePizzaManagerInterface.exc;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Accessors(chain = true)
public class PizzaException extends Exception {

    protected final HttpStatus httpStatus;

    public PizzaException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public PizzaException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }
}
