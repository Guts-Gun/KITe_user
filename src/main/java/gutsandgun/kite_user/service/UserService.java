package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.user.UserEmailDto;
import gutsandgun.kite_user.dto.user.UserPhoneDto;
import gutsandgun.kite_user.entity.write.UserEmail;
import gutsandgun.kite_user.entity.write.UserPhone;
import gutsandgun.kite_user.repository.read.ReadUserEmailRepository;
import gutsandgun.kite_user.repository.read.ReadUserPhoneRepository;
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

    @Autowired
    ReadUserPhoneRepository rUserPhoneRepository;

    @Override
    public List<UserPhoneDto> readUserPhone(String userId) {
        return rUserPhoneRepository.findByUserId(userId).stream().map(m->new UserPhoneDto(m)).collect(Collectors.toList());
    }
    @Override
    public Long createUserPhone(String userId, UserPhoneDto userPhoneDto) {
        if(wUserPhoneRepository.countByUserId(userId)<10){
            UserPhone userPhone = userPhoneDto.toEntity();
            userPhone.setUserId(userId);
            userPhone.setRegId(userId);
            Optional<UserPhone> check = wUserPhoneRepository.findByUserIdAndNameOrPhone(userId, userPhoneDto.getName(), userPhoneDto.getPhone());
            log.info(String.valueOf(check.isPresent()));
            return (!check.isPresent())? wUserPhoneRepository.save(userPhone).getId() : null;
        }
        else{
            return null;
        }
    }

    @Override
    public Long updateUserPhone(String userId, UserPhoneDto userPhoneDto) {

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
    public List<Long> deleteUserPhoneList(String userId, List<Long> userPhoneIdList) {
        return userPhoneIdList.stream().map(d->{
            Optional<UserPhone> check = wUserPhoneRepository.findById(d);
            log.info(String.valueOf(check.isPresent()));
            if(check.isPresent()){
                UserPhone userPhone = check.get();
                userPhone.setModId(userId);
                userPhone.setIsDeleted(true);
                return wUserPhoneRepository.save(userPhone).getId();
            }
            return null;
        }).collect(Collectors.toList());
    }


    //email
    @Autowired
    WriteUserEmailRepository wUserEmailRepository;
    @Autowired
    ReadUserEmailRepository rUserEmailRepository;
    @Override
    public List<UserEmailDto> readUserEmail(String userId) {
        return rUserEmailRepository.findByUserId(userId).stream().map(m->new UserEmailDto(m)).collect(Collectors.toList());
    }
    @Override
    public Long createUserEmail(String userId, UserEmailDto userEmailDto) {
        if(wUserEmailRepository.countByUserId(userId)<10){
            UserEmail userEmail = userEmailDto.toEntity();
            userEmail.setUserId(userId);
            userEmail.setRegId(userId);
            Optional<UserEmail> check = wUserEmailRepository.findByUserIdAndNameOrEmail(userId, userEmailDto.getName(), userEmailDto.getEmail());
            log.info(String.valueOf(check.isPresent()));
            return (!check.isPresent())? wUserEmailRepository.save(userEmail).getId() : null;
        }
        else{
            return null;
        }
    }

    @Override
    public Long updateUserEmail(String userId, UserEmailDto userEmailDto) {

        Optional<UserEmail> nameEmailCheck = wUserEmailRepository.findByUserIdAndNameOrEmail(userId, userEmailDto.getName(), userEmailDto.getEmail());
        Optional<UserEmail> idCheck = wUserEmailRepository.findById(userEmailDto.getId());

        log.info(String.valueOf(nameEmailCheck.isPresent())+" "+String.valueOf(idCheck.isPresent()));
        if(!nameEmailCheck.isPresent() & idCheck.isPresent()){
            UserEmail userEmail = idCheck.get();
            if(userEmailDto.getName()!=null){
                userEmail.setName(userEmailDto.getName());
            }
            userEmail.setModId(userId);
            return wUserEmailRepository.save(userEmail).getId();
        }
        else return null;
    }

    @Override
    public List<Long> deleteUserEmailList(String userId, List<Long>userEmailIdList) {
        return userEmailIdList.stream().map(d->{
            Optional<UserEmail> check = wUserEmailRepository.findById(d);
            log.info(String.valueOf(check.isPresent()));
            if(check.isPresent()){
                UserEmail userEmail = check.get();
                userEmail.setModId(userId);
                userEmail.setIsDeleted(true);
                return wUserEmailRepository.save(userEmail).getId();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
