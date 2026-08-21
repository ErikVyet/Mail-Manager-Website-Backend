package code.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.dtos.ResponseMap;
import code.services.SettingService;

@RestController
@RequestMapping("/vletter/api/v1/setting")
public class SettingController {
    
    protected SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/signature")
    public ResponseEntity<ResponseMap<String>> readSignature(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        ResponseMap<String> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.OK.value());
        responseMap.setData(this.settingService.readSignature(email));
        return ResponseEntity.ok().body(responseMap);
    }

    @PatchMapping("/signature/regenerate")
    public ResponseEntity<ResponseMap<String>> regenerateSignature(@AuthenticationPrincipal Jwt jwt) throws Exception {
        String email = jwt.getClaimAsString("email");
        ResponseMap<String> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.OK.value());
        responseMap.setMessage("Successfully regenerated signature");
        responseMap.setData(this.settingService.regenerateSignature(email));
        return ResponseEntity.ok().body(responseMap);
    }

}