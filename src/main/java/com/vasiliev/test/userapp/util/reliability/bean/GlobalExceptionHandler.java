package com.vasiliev.test.userapp.util.reliability.bean;

import com.vasiliev.test.userapp.model.OperationResult;
import com.vasiliev.test.userapp.util.reliability.OperationException;
import com.vasiliev.test.userapp.util.reliability.OperationResultResponseBuilder;
import com.vasiliev.test.userapp.util.reliability.OperationResultStatusMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.vasiliev.test.userapp.util.reliability.OperationResultStatus.FAILURE_INTERNAL_UNKNOWN;
import static com.vasiliev.test.userapp.util.reliability.OperationResultStatus.FAILURE_VALIDATION;


/**
 * The type Global exception handler.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private OperationResultStatusMapper operationResultStatusMapper;

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(final TypeMismatchException exception) {
        logger.warn(exception);
        exception.printStackTrace();
        return responseBuilder()
                .status(FAILURE_VALIDATION)
                .description(exception.getMessage())
                .response();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgumentException(final MethodArgumentNotValidException exception) {
        logger.warn(exception);
        exception.printStackTrace();
        return responseBuilder()
                .status(FAILURE_VALIDATION)
                .description(exception.getMessage())
                .response();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMessageNotReadableException(final HttpMessageNotReadableException exception) {
        logger.warn(exception);
        exception.printStackTrace();
        return responseBuilder()
                .status(FAILURE_VALIDATION)
                .description(exception.getMessage())
                .response();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupportedException(final HttpRequestMethodNotSupportedException exception) {
        logger.warn(exception);
        exception.printStackTrace();
        return responseBuilder()
                .status(FAILURE_VALIDATION)
                .description(exception.getMessage())
                .response();
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<?> handleOperationException(final OperationException exception) {
        logger.warn(exception);
        exception.printStackTrace();
        return responseBuilder()
                .status(exception.getStatus())
                .description(exception.getDescription())
                .response();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public void handleLoginException(final Exception exception) throws Exception {
        logger.warn(exception);
        exception.printStackTrace();
        throw exception;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(final Exception exception) {
        logger.warn(exception);
        exception.printStackTrace();
        return responseBuilder()
                .status(FAILURE_INTERNAL_UNKNOWN)
                .response();
    }

    private OperationResultResponseBuilder responseBuilder() {
        return new OperationResultResponseBuilder()
                .statusMapper(operationResultStatusMapper)
                .type(OperationResult.class);
    }
}
