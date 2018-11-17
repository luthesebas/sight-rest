package de.ka.sl.sight.rest.misc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sebastian Luther (@url(https://github.com/luthesebas))
 */
@ControllerAdvice
public class InstructionNotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(InstructionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handel(InstructionNotFoundException e) {
        return e.getMessage();
    }

}