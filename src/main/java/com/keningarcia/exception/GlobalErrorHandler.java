package com.keningarcia.exception;

import com.keningarcia.dto.GenericResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req) {

        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(404, "NOT FOUND", Arrays.asList(errorResponse)) , HttpStatus.NOT_FOUND);
    }*/

    //Desde Spring boot 3
    @ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest req) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Model Not Found Exception");
        pd.setType(URI.create(req.getContextPath()));
        pd.setProperty("CODE", "404");
        pd.setProperty("MESSAGE", "NOT-FOUND");
        return pd;
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest req) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(400, "BAD REQUEST", Arrays.asList(errorResponse)), HttpStatus.BAD_REQUEST);
    }*/
    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(400, "BAD REQUEST", Arrays.asList(errorResponse)) , HttpStatus.BAD_REQUEST);
    }*/
}
