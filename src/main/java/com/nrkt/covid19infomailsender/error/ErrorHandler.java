package com.nrkt.covid19infomailsender.error;

import com.nrkt.covid19infomailsender.exception.CustomNotFoundException;
import com.sun.mail.util.MailConnectException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.listener.adapter.ListenerExecutionFailedException;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({
            ConstraintViolationException.class,
            UnexpectedTypeException.class
    })
    ResponseEntity<ApiError> handleConstraintValidationException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(errorDetails(ex.getMessage(), BAD_REQUEST, request), BAD_REQUEST);
    }

    @ExceptionHandler({
            CustomNotFoundException.class,
            IllegalArgumentException.class
    })
    ResponseEntity<ApiError> handleBadRequestException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(errorDetails(ex.getMessage(), BAD_REQUEST, request), BAD_REQUEST);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    ResponseEntity<ApiError> classNotFoundException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(errorDetails(ex.getMessage() +"not found", INTERNAL_SERVER_ERROR, request), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            ListenerExecutionFailedException.class,
            JMSException.class,
            MailConnectException.class
    })
    ResponseEntity<ApiError> handleJmsListenerException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(errorDetails(ex.getMessage(), INTERNAL_SERVER_ERROR, request), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            Exception.class,
            NullPointerException.class,
            JobExecutionException.class
    })
    ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(errorDetails(ex.getMessage(), INTERNAL_SERVER_ERROR, request), INTERNAL_SERVER_ERROR);
    }

    private ApiError errorDetails(String message, HttpStatus httpStatus, HttpServletRequest request) {
        var errorDetail = ApiError.builder()
                .message(message)
                .status(httpStatus.value())
                .timestamp(new Date())
                .error(httpStatus.getReasonPhrase())
                .path(request.getRequestURI().substring(request.getContextPath().length())).build();

        log.error(errorDetail.toString());
        return errorDetail;
    }
}