package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.addr.RequestAddressDto;
import gutsandgun.kite_user.dto.addr.RequestAddressListDto;
import gutsandgun.kite_user.entity.write.*;
import gutsandgun.kite_user.repository.read.*;
import gutsandgun.kite_user.repository.write.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    //group
    private final ReadUserGroupRepository readUserGroupRepository;
    private final WriteUserGroupRepository writeUserGroupRepository;

    //group-adress
    private final ReadAddressGroupRepository readAddressGroupRepository;
    private final WriteAddressGroupRepository writeAddressGroupRepository;

    //address
    private final ReadUserAddressRepository readUserAddressRepository;
    private final WriteUserAddressRepository writeUserAddressRepository;

    private final ReadAddressPhoneRepository readAddressPhoneRepository;
    private final WriteAddressPhoneRepository writeAddressPhoneRepository;

    private final ReadAddressEmailRepository readAddressEmailRepository;
    private final WriteAddressEmailRepository writeAddressEmailRepository;


    public void createUserAddressOne(Long userId,RequestAddressDto requestAddressDto){
        //1.그룹 확인
        //1-1.그룹 확인
        UserGroup userGroup= writeUserGroupRepository.findById(requestAddressDto.getGroupId()).get();
        //1-2.그룹 userId확인
        if(userGroup.getUserId()==userId){
            //2.생성.
            createUserAddress(userId,requestAddressDto);
        }
    }
    public void createUserAddressList(Long userId,RequestAddressListDto requestAddressDtoList){
        //1.그룹 확인
        //1-1.그룹 확인
        UserGroup userGroup= writeUserGroupRepository.findById(requestAddressDtoList.getGroupId()).get();
        //1-2.그룹 userId확인
        if(userGroup.getUserId()==userId){
            //2. dto에 userid/group id 세팅
            requestAddressDtoList.getRequestAddressList().forEach(d->{
                d.setUserId(1L);
                d.setGroupId(userGroup.getId());
            });
            //3.생성
            requestAddressDtoList.getRequestAddressList().forEach(d->createUserAddress(userId,d));
        }
    }
        private void createUserAddress(Long userId,RequestAddressDto requestAddressDto){
            //0.dto세팅(user세팅)
            requestAddressDto.setUserId(userId);
            //1.주소록 존재확인(유저/이름/전화번호/이메일)
            Optional<Long> id =  Optional.empty();
            //유저/이름
            List<UserAddress> addressList = writeUserAddressRepository.findByUserIdAndName(userId,requestAddressDto.getName());
            for(UserAddress a:addressList){
                System.out.println("user/name");
                //전화번호
                Optional<AddressPhone> phone = writeAddressPhoneRepository.findByIdAndPhone(a.getId(),requestAddressDto.getPhone());
                if(phone.isPresent()) {
                    System.out.println("phone");
                    //이메일
                    Optional<AddressEmail> email = writeAddressEmailRepository.findByIdAndEmail(phone.get().getId(),requestAddressDto.getEmail());
                    if(email.isPresent()) {
                        System.out.println("email");
                        id = Optional.ofNullable(email.get().getId());
                        System.out.println(id);
                    }
                }
            }

            //2-1.주소록 없을 때
            if(!id.isPresent()){
                //2-1-1. 주소록 생성
                UserAddress userAddress = writeUserAddressRepository.save(requestAddressDto.toUserAddressEntity());
                //2-1-2. 주소록-전화번호 생성
                writeAddressPhoneRepository.save(requestAddressDto.toUserAddressPhoneEntity(userAddress.getId()));
                //2-1-3. 주소록-이메일 생성
                writeAddressEmailRepository.save(requestAddressDto.toUserAddressEmailEntity(userAddress.getId()));
                writeAddressGroupRepository.save(AddressGroup.builder()
                        .userAddressId(userAddress.getId())
                        .userGroupId(requestAddressDto.getGroupId())
                        .build());
            }
            //2-2.주소록 존재할 때
            else{
                if(!writeAddressGroupRepository.findByUserAddressIdAndUserGroupId(id.get(),requestAddressDto.getGroupId()).isPresent()) {
                    writeAddressGroupRepository.save(AddressGroup.builder()
                            .userAddressId(id.get())
                            .userGroupId(requestAddressDto.getGroupId())
                            .build());
                }
            }

        }
}
