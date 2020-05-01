package com.testtask.exception;

import com.task.constants.Constants;
import com.task.dto.response.Control;
import com.task.dto.response.GenericResponse;
import javassist.NotFoundException;
import org.hibernate.id.IdentifierGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception e, WebRequest request) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE + " : Access Denied!");
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(IdentifierGenerationException.class)
    public ResponseEntity<Object> handleIdentifierGenerationException(Exception e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<?> servletRequestBindingException(ServletRequestBindingException e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> numberFormatException(NumberFormatException e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        Control control = new Control();
        GenericResponse handledGenericResponse = new GenericResponse();
        LOGGER.info("unknow exception {}", e);
        control.setMessage(Constants.FAILURE_MESSAGE);
        control.setStatus(Constants.FAILURE_STATUS);
        handledGenericResponse.setControl(control);
        return ResponseEntity.status(HttpStatus.OK).body(handledGenericResponse);
    }
}
