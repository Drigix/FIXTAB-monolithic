package com.fixtab.app.exceptions.handlers;

import com.fixtab.app.exceptions.InvalidEmailException;
import com.fixtab.app.exceptions.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

        @ExceptionHandler(value = InvalidEmailException.class)
        @ResponseBody
        @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
        public ExceptionResponse handleException(InvalidEmailException e) {
            return new ExceptionResponse(e.getMessage());
        }
}
