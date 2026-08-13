package code.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.dtos.ResponseMap;

@RestController
@RequestMapping("/vletter/api/health")
public class HealthController {
    
    @GetMapping("/")
    public ResponseEntity<ResponseMap<String>> getHealthInfo() {
        ResponseMap<String> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.OK.value());
        responseMap.setMessage("Health: OK");
        return ResponseEntity.ok().body(responseMap);
    }

}