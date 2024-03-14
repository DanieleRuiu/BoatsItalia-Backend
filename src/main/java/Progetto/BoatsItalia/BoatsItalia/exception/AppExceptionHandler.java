package Progetto.BoatsItalia.BoatsItalia.exception;

import Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO.HttpErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice // Indica che questa classe fornisce gestione delle eccezioni per i controller
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    // Gestore delle eccezioni BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpErrorRes> badRequestHandler(BadRequestException e) {
        return new ResponseEntity<>(new HttpErrorRes(HttpStatus.BAD_REQUEST,
                "Bad request", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Gestore delle eccezioni NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpErrorRes> notFoundHandler(NotFoundException e) {
        return new ResponseEntity<>(new HttpErrorRes(HttpStatus.NOT_FOUND,
                "Not found", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Gestore delle eccezioni InternalServerErrorException
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<HttpErrorRes> internalServerErrorHandler(InternalServerErrorException e) {
        return genericExceptionHandler(e);
    }

    // Gestore generico delle eccezioni
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorRes> genericExceptionHandler(Exception e) {
        return new ResponseEntity<>(new HttpErrorRes(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Gestore delle eccezioni AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpErrorRes> accessDeniedHandler(AccessDeniedException e) {
        return new ResponseEntity<>(new HttpErrorRes(HttpStatus.UNAUTHORIZED,
                "Unauthorized", "You don't have permissions to access this resource"), HttpStatus.UNAUTHORIZED);
    }

    // Gestore delle eccezioni UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<HttpErrorRes> accessDeniedHandler(UnauthorizedException e) {
        return new ResponseEntity<>(new HttpErrorRes(HttpStatus.UNAUTHORIZED,
                "Unauthorized", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
