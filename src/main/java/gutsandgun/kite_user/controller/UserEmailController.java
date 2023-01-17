package gutsandgun.kite_user.controller;


import gutsandgun.kite_user.dto.user.UserEmailDto;
import gutsandgun.kite_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/email")
public class UserEmailController {

    @Autowired
    UserService userService;

    @GetMapping("")
    List<UserEmailDto> readUserEmail(){
        Long userId = 1L;
        return userService.readUserEmail(userId);
    }

    @PostMapping("")
    Long createUserEmail(@RequestBody UserEmailDto userEmailDto){
        Long userId = 1L;
        return userService.createUserEmail(userId,userEmailDto);
    }

    @PutMapping("")
    Long updateUserEmail(@RequestBody UserEmailDto userEmailDto){
        Long userId = 1L;
        return userService.updateUserEmail(userId,userEmailDto);
    }

    @DeleteMapping("")
    List<Long> deleteUserEmail(@RequestBody List<UserEmailDto> userEmailDtoList){
        Long userId = 1L;
        return userService.deleteUserEmailList(userId,userEmailDtoList);
    }

}
