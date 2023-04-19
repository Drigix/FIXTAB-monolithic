package com.fixtab.app.exceptions.handlers;

import com.fixtab.app.exceptions.*;
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

    @ExceptionHandler({EmailAlreadyExistsException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ExceptionResponse handleEmailAlreadyExistsException(RuntimeException e) {
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler({ItemNoLongerExistsException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleEntitiesThatNoLongerExist(RuntimeException e) {
        return new ExceptionResponse(e.getMessage());
    }


}
