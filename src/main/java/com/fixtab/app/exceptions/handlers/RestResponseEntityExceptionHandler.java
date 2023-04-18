package com.fixtab.app.exceptions.handlers;

import com.fixtab.app.exceptions.AccountAlreadyExistsException;
import com.fixtab.app.exceptions.InvalidEmailException;
import com.fixtab.app.exceptions.InvalidPasswordException;
import com.fixtab.app.exceptions.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidEmailException.class, InvalidPasswordException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleAuthenticationException(RuntimeException e) {
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler({AccountAlreadyExistsException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ExceptionResponse handleException(RuntimeException e) {
        return new ExceptionResponse(e.getMessage());
    }
}
