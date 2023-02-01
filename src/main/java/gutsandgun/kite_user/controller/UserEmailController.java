package gutsandgun.kite_user.controller;


import gutsandgun.kite_user.dto.user.UserEmailDto;
import gutsandgun.kite_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/email")
public class UserEmailController {

    @Autowired
    UserService userService;

    @GetMapping("")
    List<UserEmailDto> readUserEmail(Principal principal){
        String userId = getUser(principal);

        return userService.readUserEmail(userId);
    }

    @PostMapping("")
    Long createUserEmail(Principal principal,@RequestBody UserEmailDto userEmailDto){
        String userId = getUser(principal);

        return userService.createUserEmail(userId,userEmailDto);
    }

    @PutMapping("")
    Long updateUserEmail(Principal principal,@RequestBody UserEmailDto userEmailDto){
        String userId = getUser(principal);

        return userService.updateUserEmail(userId,userEmailDto);
    }

    @DeleteMapping("")
    List<Long> deleteUserEmail(Principal principal,@RequestBody List<Long>userEmailIdList){
        String userId = getUser(principal);

        return userService.deleteUserEmailList(userId,userEmailIdList);
    }

    public String getUser(Principal principal){
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userId = token.getTokenAttributes().get("preferred_username").toString();

        System.out.printf("getUser: ");
        System.out.println(userId);

        return userId;
    }
}
