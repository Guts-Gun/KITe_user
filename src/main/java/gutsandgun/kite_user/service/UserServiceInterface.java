package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.user.UserEmailDto;
import gutsandgun.kite_user.dto.user.UserPhoneDto;

import java.util.List;

public interface UserServiceInterface {
    //번호
    List<UserPhoneDto> readUserPhone(Long userId);
    Long createUserPhone(Long userId, UserPhoneDto userPhoneDto);
    Long updateUserPhone(Long userId, UserPhoneDto userPhoneDto);
    List<Long> deleteUserPhoneList(Long userId, List<UserPhoneDto> userPhoneList);


    //email
    List<UserEmailDto> readUserEmail(Long userId);
    Long createUserEmail(Long userId, UserEmailDto userEmailDto);
    Long updateUserEmail(Long userId, UserEmailDto userEmailDto);
    List<Long> deleteUserEmailList(Long userId, List<UserEmailDto> userEmailDtoList );
}
