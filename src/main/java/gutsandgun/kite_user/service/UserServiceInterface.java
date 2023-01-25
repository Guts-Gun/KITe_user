package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.user.UserEmailDto;
import gutsandgun.kite_user.dto.user.UserPhoneDto;

import java.util.List;

public interface UserServiceInterface {
    //번호
    List<UserPhoneDto> readUserPhone(String userId);
    Long createUserPhone(String userId, UserPhoneDto userPhoneDto);
    Long updateUserPhone(String userId, UserPhoneDto userPhoneDto);
    List<Long> deleteUserPhoneList(String userId, List<Long>userPhoneIdList);


    //email
    List<UserEmailDto> readUserEmail(String userId);
    Long createUserEmail(String userId, UserEmailDto userEmailDto);
    Long updateUserEmail(String userId, UserEmailDto userEmailDto);
    List<Long> deleteUserEmailList(String userId, List<Long>userEmailIdList );
}
