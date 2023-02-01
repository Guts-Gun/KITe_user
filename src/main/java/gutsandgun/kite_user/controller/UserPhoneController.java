package gutsandgun.kite_user.controller;


import gutsandgun.kite_user.dto.user.UserPhoneDto;
import gutsandgun.kite_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/phone")
public class UserPhoneController {

    @Autowired
    UserService userService;

    @GetMapping("")
    List<UserPhoneDto> readUserPhone(Principal principal){
        String userId = getUser(principal);

        return userService.readUserPhone(userId);
    }

    @PostMapping("")
    Long createUserPhone(Principal principal,@RequestBody UserPhoneDto userPhoneDto){
        String userId = getUser(principal);

        return userService.createUserPhone(userId,userPhoneDto);
    }

    @PutMapping("")
    Long updateUserPhone(Principal principal,@RequestBody UserPhoneDto userPhoneDto){
        String userId = getUser(principal);

        return userService.updateUserPhone(userId,userPhoneDto);
    }

    @DeleteMapping("")
    List<Long> deleteUserPhone(Principal principal,@RequestBody List<Long>userPhoneIdList){
        String userId = getUser(principal);

        return userService.deleteUserPhoneList(userId,userPhoneIdList);
    }


    public String getUser(Principal principal){
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userId = token.getTokenAttributes().get("preferred_username").toString();

        System.out.printf("getUser: ");
        System.out.println(userId);

        return userId;
    }

}
