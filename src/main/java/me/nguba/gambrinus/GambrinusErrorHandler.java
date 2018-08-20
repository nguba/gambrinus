package me.nguba.gambrinus;

import me.nguba.gambrinus.ddd.validation.ValidationFailed;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ControllerAdvice
public class GambrinusErrorHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({ ValidationFailed.class })
    public ResponseEntity<Object> validationError(final ValidationFailed ex,
                                                  final WebRequest request)
    {
        return handleExceptionInternal(ex,
                                       ex.getErrors().toString(),
                                       new HttpHeaders(),
                                       HttpStatus.CONFLICT,
                                       request);
    }
}
