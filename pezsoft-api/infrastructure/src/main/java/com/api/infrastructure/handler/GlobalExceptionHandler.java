package com.api.infrastructure.handler;

import com.api.domain.exception.BadRequestException;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException e, WebRequest request) {
        log.error("No encontrado: ", e);
        return buildResponse(e.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException e, WebRequest request) {
        log.error("Conflicto en el servicio: ", e);
        return buildResponse(e.getMessage(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException e, WebRequest request) {
        log.error("Solicitud incorrecta: ", e);
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e, WebRequest request) {
        log.error("Erro de validación: ", e);

        Map<String, String> errores = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return buildResponse(errores, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handlePersistence(PersistenceException e, WebRequest request) {
        log.error("Error de persistencia: ", e);
        return buildResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlegeneral(Exception e) {
        log.error("Error inesperado: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(Object message, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
