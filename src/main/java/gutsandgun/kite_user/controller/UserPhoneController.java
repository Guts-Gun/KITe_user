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
        Long userId = 1L;
        return userService.readUserPhone(userId);
    }

    @PostMapping("")
    Long createUserPhone(@RequestBody UserPhoneDto userPhoneDto){
        Long userId = 1L;
        return userService.createUserPhone(userId,userPhoneDto);
    }

    @PutMapping("")
    Long updateUserPhone(@RequestBody UserPhoneDto userPhoneDto){
        Long userId = 1L;
        return userService.updateUserPhone(userId,userPhoneDto);
    }

    @DeleteMapping("")
    List<Long> deleteUserPhone(@RequestBody List<Long>userPhoneIdList){
        Long userId = 1L;
        return userService.deleteUserPhoneList(userId,userPhoneIdList);
    }


}
