package code.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}