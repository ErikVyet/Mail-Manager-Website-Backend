package code.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.dtos.ResponseMap;
import code.dtos.UserDto;
import code.enums.UserStatus;
import code.services.UserService;

@RestController
@RequestMapping("/vletter/api/v1/user")
public class UserController {

    protected UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-info")
    public ResponseEntity<ResponseMap<UserDto>> readUser(@AuthenticationPrincipal Jwt jwt) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName(jwt.getClaimAsString("name"));
        userDto.setEmail(jwt.getClaimAsString("email"));
        userDto.setAvatar(jwt.getClaimAsString("avatar"));
        userDto.setStatus(UserStatus.Online);

        ResponseMap<UserDto> responseMap = new ResponseMap<>();
        responseMap.setStatus(HttpStatus.OK.value());
        responseMap.setData(this.userService.createAndReadUser(userDto));
        return ResponseEntity.ok().body(responseMap);
    }

}