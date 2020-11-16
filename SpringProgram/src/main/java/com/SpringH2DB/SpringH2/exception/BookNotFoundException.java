package com.SpringH2DB.SpringH2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String msg) {
        super(msg);
    }

    public BookNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
