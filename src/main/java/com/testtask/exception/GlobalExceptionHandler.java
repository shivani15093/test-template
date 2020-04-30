package com.testtask.exception;

import com.testtask.constants.Constants;
import com.testtask.dto.Control;
import com.testtask.dto.Response;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception e, WebRequest request) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE + " : Access Denied!");
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<?> servletRequestBindingException(ServletRequestBindingException e) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception e) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> numberFormatException(NumberFormatException e) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        Control control = new Control();
        Response handledResponse = new Response();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledResponse);
    }
}
