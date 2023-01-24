package com.plexus.prueba.controller.handler;

import com.plexus.prueba.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<GenericResponse> statusExceptionHandler(ResponseStatusException e) {
        return new ResponseEntity<>(GenericResponse.builder().status(e.getStatus().toString())
                .message(e.getReason()).build(), e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> generalExceptionHandler(Exception e, WebRequest request) {
        return new ResponseEntity<>(GenericResponse.builder().status(
                HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message("Error interno").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
