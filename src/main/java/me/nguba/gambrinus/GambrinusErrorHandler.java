/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.nguba.gambrinus.ddd.validation.ValidationFailed;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ControllerAdvice
public class GambrinusErrorHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<Object> handleNotFound(final NoSuchElementException ex,
                                                 final WebRequest request)
    {
        return handleExceptionInternal(ex,
                                       ex.getMessage(),
                                       new HttpHeaders(),
                                       HttpStatus.NOT_FOUND,
                                       request);
    }

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
