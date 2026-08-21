package code.controllers;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.dtos.ApiDto;
import code.dtos.ResponseMap;
import code.services.ApiService;

@RestController
@RequestMapping("/vletter/api/v1/dev")
public class ApiController {
    
    protected ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMap<List<ApiDto>>> readUserApiKeys(
        @AuthenticationPrincipal Jwt jwt, 
        @RequestParam(required = false) OffsetDateTime fromDate, 
        @RequestParam(required = false) OffsetDateTime toDate
    ) {
        String email = jwt.getClaimAsString("email");
        ResponseMap<List<ApiDto>> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.OK.value());
        responseMap.setData(this.apiService.readUserApiKeys(email, fromDate, toDate));
        return ResponseEntity.ok().body(responseMap);
    }

    @PostMapping("/create-api-key")
    public ResponseEntity<ResponseMap<ApiDto>> createApiKey(@AuthenticationPrincipal Jwt jwt) throws Exception {
        String email = jwt.getClaimAsString("email");
        ResponseMap<ApiDto> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.CREATED.value());
        responseMap.setMessage("Successfully created new API key");
        responseMap.setData(this.apiService.createApiKey(email));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseMap);
    }

    @DeleteMapping("/delete-api-key/{key}")
    public ResponseEntity<ResponseMap<?>> deleteApiKey(
        @AuthenticationPrincipal Jwt jwt, 
        @PathVariable String key
    ) {
        String email = jwt.getClaimAsString("email");
        this.apiService.deleteApiKey(email, key);
        ResponseMap<ApiDto> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.OK.value());
        responseMap.setMessage("Successfully deleted API key");
        responseMap.setData(null);
        return ResponseEntity.ok().body(responseMap);
    }

}