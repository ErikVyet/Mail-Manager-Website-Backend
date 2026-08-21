package code.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import code.dtos.ResponseMap;
import code.exceptions.UnauthorizedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMap<?>> handleException(Exception exception) {
        ResponseMap<String> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("An error has occured");
        responseMap.setData(null);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseMap<?>> handleUnauthorizedException(UnauthorizedException exception) {
        ResponseMap<String> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.UNAUTHORIZED.value());
        responseMap.setMessage(exception.getMessage());
        responseMap.setData(null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class })
    public ResponseEntity<ResponseMap<Object>> handleConstraintViolationException(Exception exception) {
        ResponseMap<Object> responseMap = new ResponseMap<>();

        if (exception instanceof ConstraintViolationException) {
            String constraint = ((ConstraintViolationException) exception).getConstraintName();
            Map<String, String> errorFields = new HashMap<>();

            if (constraint.equals("uk6dotkott2kjsp8vw4d0m25fb7")) {
                errorFields.put("email", "Email already existed");
            }
            if (constraint.equals("ukiolw98qy0bp9gqo2u0pnxmx39")) {
                errorFields.put("setting", "User already has setting saved");
            }
            if (constraint.equals("uko3dgu4ib8lxg12lvmnvx5g8ls")) {
                errorFields.put("key", "API key already existed");
            }

            responseMap.setData(errorFields);
        }

        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Invalid data");
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMap<Map<String, String>>> handleValidationException(MethodArgumentNotValidException exception) {
        ResponseMap<Map<String, String>> responseMap = new ResponseMap<>();
        Map<String, String> errorFields = new HashMap<>();

        exception.getFieldErrors().forEach(errorField -> {
            errorFields.put(errorField.getField(), errorField.getDefaultMessage());
        });

        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Data validations failed");
        responseMap.setData(errorFields);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ResponseMap<Map<String, String>>> handleValidationException(HandlerMethodValidationException exception) {
        ResponseMap<Map<String, String>> responseMap = new ResponseMap<>();
        Map<String, String> errorFields = new HashMap<>();

        exception.getParameterValidationResults().forEach(result -> {
            result.getResolvableErrors().forEach(resolve -> {
                errorFields.put(
                    result.getMethodParameter().getParameterName(),
                    resolve.getDefaultMessage()
                );
            });
        });

        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Data validations failed");
        responseMap.setData(errorFields);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseMap<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ResponseMap<?> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Invalid data type");
        responseMap.setData(null);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseMap<?>> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        ResponseMap<?> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Missing mandatory data from request header");
        responseMap.setData(null);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler({ MissingServletRequestPartException.class, MissingServletRequestParameterException.class })
    public ResponseEntity<ResponseMap<?>> handleMissingFormDataException(Exception exception) {
        ResponseMap<?> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Missing mandatory form data");
        responseMap.setData(null);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMap<?>> handleMissingRequestBodyException(HttpMessageNotReadableException exception) {
        ResponseMap<?> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("Missing mandatory data from request body");
        responseMap.setData(null);
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseMap<?>> handleNoSuchElementException(NoSuchElementException exception) {
        ResponseMap<?> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.NOT_FOUND.value());
        responseMap.setMessage("Can not find mandatory data");
        responseMap.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
    }

}