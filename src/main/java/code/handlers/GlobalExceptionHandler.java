package code.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import code.dtos.ResponseMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMap<String>> handleException(Exception exception) {
        ResponseMap<String> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.BAD_REQUEST.value());
        responseMap.setMessage("An error has occured");
        responseMap.setData(null);
        return ResponseEntity.badRequest().body(responseMap);
    }

}