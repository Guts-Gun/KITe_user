package gutsandgun.kite_user.controller;


import gutsandgun.kite_user.dto.user.UserPhoneDto;
import gutsandgun.kite_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/phone")
public class UserPhoneController {

    @Autowired
    UserService userService;

    @GetMapping("")
    List<UserPhoneDto> readUserPhone(){
        String userId = "solibtest";
        return userService.readUserPhone(userId);
    }

    @PostMapping("")
    Long createUserPhone(@RequestBody UserPhoneDto userPhoneDto){
        String userId = "solibtest";
        return userService.createUserPhone(userId,userPhoneDto);
    }

    @PutMapping("")
    Long updateUserPhone(@RequestBody UserPhoneDto userPhoneDto){
        String userId = "solibtest";
        return userService.updateUserPhone(userId,userPhoneDto);
    }

    @DeleteMapping("")
    List<Long> deleteUserPhone(@RequestBody List<Long>userPhoneIdList){
        String userId = "solibtest";
        return userService.deleteUserPhoneList(userId,userPhoneIdList);
    }


}
