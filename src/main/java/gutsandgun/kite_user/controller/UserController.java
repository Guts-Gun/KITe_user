package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.user.UserDTO;
import gutsandgun.kite_user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity authenticateUser(@RequestBody UserDTO userDTO) {
        if(!userService.existsByUsername(String.valueOf(userDTO.getId()))) {
            UserDTO keycloakUser = userService.createUser(userDTO);
            //userService.registerUser(keycloakUser);
        }

        AccessTokenResponse tokenRes = userService.setAuth(userDTO); // Token 발급

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", tokenRes);
        //if(tokenRes != null){
        //    data.put("info", userService.getUserDetailInfo(map.get("username")));
        //}
        Map<String, Object> resultmap = new HashMap<String, Object>();
        resultmap.put("data", data);
        return ResponseEntity.ok(resultmap);
    }


}
