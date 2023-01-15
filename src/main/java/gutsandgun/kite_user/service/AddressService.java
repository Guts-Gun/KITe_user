package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.Address.plus.RequestAddressDto;
import gutsandgun.kite_user.dto.Address.plus.RequestAddressListDto;
import gutsandgun.kite_user.entity.write.AddressGroup;
import gutsandgun.kite_user.entity.write.AddressPhone;
import gutsandgun.kite_user.entity.write.UserAddress;
import gutsandgun.kite_user.entity.write.UserGroup;
import gutsandgun.kite_user.repository.read.*;
import gutsandgun.kite_user.repository.write.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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


    public void createUserAddressOne(RequestAddressDto requestAddressDto){
        //1. 그룹 존재 확인
        //1-1. user확인
        //
        //1-2.그룹 확인
        UserGroup userGroup= writeUserGroupRepository.findById(requestAddressDto.getGroupId()).get();
        //2.생성.
        createUserAddress(requestAddressDto);
    }
    public void createUserAddressList(RequestAddressListDto requestAddressDtoList){
        //1. 존재 확인
        //1-1. user확인
        //
        //1-2. 그룹 확인
        UserGroup userGroup= writeUserGroupRepository.findById(requestAddressDtoList.getGroupId()).get();
        //2. dto에 userid/group id 세팅
        requestAddressDtoList.getRequestAddressDtoList().forEach(d->{
            d.setUserId(1L);
            d.setGroupId(userGroup.getId());
        });
        //3.생성
        requestAddressDtoList.getRequestAddressDtoList().forEach(d->createUserAddress(d));
    }
        private void createUserAddress(RequestAddressDto requestAddressDto){
            UserAddress userAddress = writeUserAddressRepository.save(requestAddressDto.toUserAddressEntity());
            //1. 주소록-전화번호 생성
            writeAddressPhoneRepository.save(requestAddressDto.toUserAddressPhoneEntity(userAddress.getId()));
            //2. 주소록-이메일 생성
            writeAddressEmailRepository.save(requestAddressDto.toUserAddressEmailEntity(userAddress.getId()));
            //3. 주소록 - 그룹 생성
            writeAddressGroupRepository.save(AddressGroup.builder()
                    .userAddressId(userAddress.getId())
                    .userGroupId(requestAddressDto.getGroupId())
                    .build());
        }
}
