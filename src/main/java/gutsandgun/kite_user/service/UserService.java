package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.user.UserEmailDto;
import gutsandgun.kite_user.dto.user.UserPhoneDto;
import gutsandgun.kite_user.entity.write.UserPhone;
import gutsandgun.kite_user.repository.write.WriteUserEmailRepository;
import gutsandgun.kite_user.repository.write.WriteUserPhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    Logger log = LoggerFactory.getLogger(this.getClass());

    //phone
    @Autowired
    WriteUserPhoneRepository wUserPhoneRepository;
    @Override
    public List<UserPhoneDto> readUserPhone(Long userId) {
        return wUserPhoneRepository.findByUserId(userId).stream().map(m->new UserPhoneDto(m)).collect(Collectors.toList());
    }
    @Override
    public Long createUserPhone(Long userId, UserPhoneDto userPhoneDto) {
        if(wUserPhoneRepository.countByUserId(userId)<10){
            UserPhone userPhone = userPhoneDto.toEntity();
            Optional<UserPhone> check = wUserPhoneRepository.findByUserIdAndNameOrPhone(userId, userPhoneDto.getName(), userPhoneDto.getPhone());
            log.info(String.valueOf(check.isPresent()));
            return (!check.isPresent())? wUserPhoneRepository.save(userPhone).getId() : null;
        }
        else{
            return null;
        }
    }

    @Override
    public Long updateUserPhone(Long userId, UserPhoneDto userPhoneDto) {

        Optional<UserPhone> namePhoneCheck = wUserPhoneRepository.findByUserIdAndNameOrPhone(userId, userPhoneDto.getName(), userPhoneDto.getPhone());
        Optional<UserPhone> idCheck = wUserPhoneRepository.findById(userPhoneDto.getId());

        log.info(String.valueOf(namePhoneCheck.isPresent())+" "+String.valueOf(idCheck.isPresent()));
        if(!namePhoneCheck.isPresent() & idCheck.isPresent()){
            UserPhone userPhone = idCheck.get();
            if(userPhoneDto.getName()!=null){
                userPhone.setName(userPhoneDto.getName());
            }
            userPhone.setModId(userId);
            return wUserPhoneRepository.save(userPhone).getId();
        }
        else return null;
    }

    @Override
    public List<Long> deleteUserPhoneList(Long userId, List<UserPhoneDto> userPhoneList) {
        return userPhoneList.stream().map(d->{
            Optional<UserPhone> check = wUserPhoneRepository.findById(d.getId());
            log.info(String.valueOf(check.isPresent()));
            if(check.isPresent()){
                UserPhone userPhone = check.get();
                userPhone.setIsDeleted(true);
                return wUserPhoneRepository.save(userPhone).getId();
            }
            return null;
        }).collect(Collectors.toList());
    }


    //email
    @Autowired
    WriteUserEmailRepository wUserEmailRepository;
    @Override
    public List<UserEmailDto> readUserEmail(Long userId) {
        return null;
    }

    @Override
    public Long createUserEmail(Long userId, UserEmailDto userEmailDto) {
        return null;
    }

    @Override
    public Long updateUserEmail(Long userId, UserEmailDto userEmailDto) {
        return null;
    }

    @Override
    public Long deleteUserEmailList(Long userId, List<UserEmailDto> userEmailDtoList) {
        return null;
    }
}
