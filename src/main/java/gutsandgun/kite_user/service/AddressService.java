package gutsandgun.kite_user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gutsandgun.kite_user.dto.addr.*;
import gutsandgun.kite_user.entity.write.*;
import gutsandgun.kite_user.repository.read.*;
import gutsandgun.kite_user.repository.write.*;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {
    ObjectMapper mapper = new ObjectMapper();

    //group
    private final ReadUserGroupRepository rUserGroupRepository;
    private final WriteUserGroupRepository wUserGroupRepository;

    //group-adress
    private final ReadAddressGroupRepository rAddressGroupRepository;
    private final WriteAddressGroupRepository wAddressGroupRepository;

    //address
    private final ReadUserAddressRepository rUserAddressRepository;
    private final WriteUserAddressRepository wUserAddressRepository;

    private final ReadAddressPhoneRepository rAddressPhoneRepository;
    private final WriteAddressPhoneRepository wAddressPhoneRepository;

    private final ReadAddressEmailRepository rAddressEmailRepository;
    private final WriteAddressEmailRepository wAddressEmailRepository;
    private final WriteUserPhoneRepository wPhoneRepository;


    public List<ResponseAddressWithGroupDto> getUserAddressList(String userId){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserId(userId).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }

    public List<ResponseAddressWithGroupDto> getUserAddressListFilterName(String userId, String name){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserIdAndNameContaining(userId,name).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        System.out.println(responseAddressDtoList.size());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }

    public List<ResponseAddressWithGroupDto> getUserAddressListFilterPhone(String userId, String phone){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserIdAndPhoneContaining(userId,phone).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        System.out.println(responseAddressDtoList.size());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }

    public List<ResponseAddressWithGroupDto> getUserAddressListFilterEmail(String userId, String email){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserIdAndEmailContaining(userId,email).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        System.out.println(responseAddressDtoList.size());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }


    public List<ResponseAddressWithGroupDto> getUserAddressPage(String userId, Pageable pageable){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserId(userId,pageable).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }

    public List<ResponseAddressWithGroupDto> getUserAddressPageFilterName(String userId, String name, Pageable pageable){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserIdAndNameContaining(userId,name,pageable).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        System.out.println(responseAddressDtoList.size());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }

    public List<ResponseAddressWithGroupDto> getUserAddressPageFilterPhone(String userId, String phone, Pageable pageable){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserIdAndPhoneContaining(userId,phone,pageable).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        System.out.println(responseAddressDtoList.size());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }

    public List<ResponseAddressWithGroupDto> getUserAddressPageFilterEmail(String userId, String email, Pageable pageable){
        List<ResponseAddressDto> responseAddressDtoList = rUserAddressRepository.findUserAddressByUserIdAndEmailContaining(userId,email,pageable).stream().map((d)->(mapper.convertValue(d,ResponseAddressDto.class))).collect(Collectors.toList());
        System.out.println(responseAddressDtoList.size());
        return responseAddressDtoList.stream().map(d -> (getResponseAddressDetailDto(d.getUserAddressId(), d))).collect(Collectors.toList());
    }



            public ResponseAddressDto getResponseAddressDto(Long addressId){
                Optional<gutsandgun.kite_user.entity.read.UserAddress> checkAddress = rUserAddressRepository.findById(addressId);
                if(checkAddress.isPresent()){
                    //이름/id
                    gutsandgun.kite_user.entity.read.UserAddress userAddress = checkAddress.get();
                    Long id = userAddress.getId();
                    String name = userAddress.getName();

                    //email/phone
                    String phone = null;
                    String email = null;
                    Optional<AddressPhone> checkPhone = rAddressPhoneRepository.findByUserAddressId(id);
                    Optional<AddressEmail> checkEmail = rAddressEmailRepository.findByUserAddressId(id);
                    if(checkPhone.isPresent()){
                        phone = checkPhone.get().getPhone();
                    }
                    if(checkEmail.isPresent()){
                        email = checkEmail.get().getEmail();
                    }
                    ResponseAddressDto responseAddressDto = new ResponseAddressDto(id,name,email,phone);
                    return responseAddressDto;
                }
                return null;
            }

            public ResponseAddressWithGroupDto getResponseAddressDetailDto(Long addressId, ResponseAddressDto responseAddressDto){
                //group
                List<ResponseBelongGroupDto> responseBelongGroupDtoList = new ArrayList<>();
                Optional<gutsandgun.kite_user.entity.read.UserAddress> checkAddress = rUserAddressRepository.findById(addressId);
                if(checkAddress.isPresent()) {
                    gutsandgun.kite_user.entity.read.UserAddress userAddress = checkAddress.get();
                    List<gutsandgun.kite_user.entity.read.AddressGroup> addressGroupList = rAddressGroupRepository.findByUserAddressId(userAddress.getId());
                    addressGroupList.stream().forEach(d->{
                        Optional<gutsandgun.kite_user.entity.read.UserGroup> checkUserGroup = rUserGroupRepository.findById(d.getUserGroupId());
                        if(checkUserGroup.isPresent()){
                            gutsandgun.kite_user.entity.read.UserGroup userGroup = checkUserGroup.get();
                            ResponseBelongGroupDto responseBelongGroupDto = new ResponseBelongGroupDto(userGroup.getId(),userGroup.getGroupName());
                            responseBelongGroupDtoList.add(responseBelongGroupDto);
                        }
                    });
                }
                return(new ResponseAddressWithGroupDto(responseAddressDto,responseBelongGroupDtoList));
            }



    public void createUserAddressOne(String userId,RequestAddressDto requestAddressDto){
        //1.그룹-유저 확인
        Optional<UserGroup> userGroup= wUserGroupRepository.findByIdAndUserId(requestAddressDto.getGroupId(),userId);
        if(userGroup.isPresent() || requestAddressDto.getGroupId()==-1){
            System.out.println("group enable");
            //2.생성.
            createUserAddress(userId,requestAddressDto);
        }
    }
    public void createUserAddressList(String userId,RequestAddressListDto requestAddressListDto){
        //1.그룹-유저 확인
        Optional<UserGroup> userGroup= wUserGroupRepository.findByIdAndUserId(requestAddressListDto.getGroupId(),userId);
        if(userGroup.isPresent()){
            System.out.println("group enable");
            //2-1. dto에 group id 세팅
            requestAddressListDto.getRequestAddressList().forEach(d->{
                d.setGroupId(userGroup.get().getId());
            });
        }
        else if(requestAddressListDto.getGroupId()==-1){
            //2-2. dto에 group id 세팅 (그룹 미지정)
            requestAddressListDto.getRequestAddressList().forEach(d->{
                d.setGroupId(-1L);
            });
        }
        //생성
        requestAddressListDto.getRequestAddressList().forEach(d->createUserAddress(userId,d));
    }
        private void createUserAddress(String userId,RequestAddressDto requestAddressDto){
            //0.dto세팅
            requestAddressDto.setUserId(userId);

            //1.주소록 존재확인(유저/이름/전화번호/이메일)
            Optional<Long> id =  Optional.empty();
            //유저/이름
            List<UserAddress> addressList = wUserAddressRepository.findByUserIdAndName(userId,requestAddressDto.getName());
            for(UserAddress a:addressList){
                System.out.println("user/name");
                //1.1.이메일/전화번호 모두 존재
                if(requestAddressDto.getPhone()!=null && requestAddressDto.getEmail()!=null){
                    //전화번호
                    Optional<AddressPhone> phone = wAddressPhoneRepository.findByUserAddressIdAndPhone(a.getId(),requestAddressDto.getPhone());
                    if(phone.isPresent()) {
                        System.out.println("phone");
                        //이메일
                        Optional<AddressEmail> email = wAddressEmailRepository.findByUserAddressIdAndEmail(phone.get().getId(),requestAddressDto.getEmail());
                        if(email.isPresent()) {
                            System.out.println("email");
                            id = Optional.ofNullable(email.get().getId());
                        }
                    }
                }
                //1.2.전화번호만 존재
                else if(requestAddressDto.getPhone()!=null){
                    //전화번호
                    Optional<AddressPhone> phone = wAddressPhoneRepository.findByUserAddressIdAndPhone(a.getId(),requestAddressDto.getPhone());
                    if(phone.isPresent() && !wAddressEmailRepository.findByUserAddressId(a.getId()).isPresent()) {
                        id = Optional.ofNullable(a.getId());
                    }
                }
                //1.3.이메일만 존재
                else if(requestAddressDto.getEmail()!=null){
                    //이메일
                    Optional<AddressEmail> email = wAddressEmailRepository.findByUserAddressIdAndEmail(a.getId(),requestAddressDto.getEmail());
                    if(email.isPresent() && !wAddressPhoneRepository.findByUserAddressId(a.getId()).isPresent()) {
                        id = Optional.ofNullable(a.getId());
                    }
                }

            }
            //2-1.주소록 없을 때
            if(!id.isPresent()){
                //2-1-1. 주소록 생성
                UserAddress userAddress = wUserAddressRepository.save(requestAddressDto.toUserAddressEntity());
                //2-1-2. 주소록-전화번호 생성
                if(requestAddressDto.getPhone()!=null){
                    wAddressPhoneRepository.save(requestAddressDto.toUserAddressPhoneEntity(userAddress.getId()));
                }
                //2-1-3. 주소록-이메일 생성
                if(requestAddressDto.getEmail()!=null){
                    wAddressEmailRepository.save(requestAddressDto.toUserAddressEmailEntity(userAddress.getId()));
                }

                //2-1-4. 그룹 매핑(-1은 그룹지정x)
                if(requestAddressDto.getGroupId()!=-1L){
                    wAddressGroupRepository.save(AddressGroup.builder()
                            .userAddressId(userAddress.getId())
                            .userGroupId(requestAddressDto.getGroupId())
                            .regId(userId)
                            .build());
                }
            }
            //2-2.주소록 존재할 때
            else{
                System.out.println(id.get());
                //2-2.그룹 매핑(-1은 그룹지정x)
                if(requestAddressDto.getGroupId()!=-1L) {
                    if (!wAddressGroupRepository.findByUserAddressIdAndUserGroupId(id.get(), requestAddressDto.getGroupId()).isPresent()) {
                        wAddressGroupRepository.save(AddressGroup.builder()
                                .userAddressId(id.get())
                                .userGroupId(requestAddressDto.getGroupId())
                                .regId(userId)
                                .build());
                    }
                }
            }

        }

    public void deleteUserAddress(String userId,List<Long> deleteIdList){
        deleteIdList.stream().forEach(d->{
            //주소록 삭제
            Optional<UserAddress> userAddress = wUserAddressRepository.findByIdAndUserId(d,userId);
            if(userAddress.isPresent()){
                UserAddress value = userAddress.get();
                value.setModId(userId);
                value.setIsDeleted(true);
                wUserAddressRepository.save(value);


                //주소록 id
                Long userAddressId = userAddress.get().getId();

                //전화번호 삭제
                Optional<AddressPhone> addressPhone = wAddressPhoneRepository.findByUserAddressId(userAddressId);
                if(addressPhone.isPresent()){
                    AddressPhone v = addressPhone.get();
                    v.setModId(userId);
                    v.setIsDeleted(true);
                    wAddressPhoneRepository.save(v);
                }

                //이메일 삭제
                Optional<AddressEmail> addressEmail = wAddressEmailRepository.findByUserAddressId(userAddressId);
                if(addressEmail.isPresent()){
                    AddressEmail v = addressEmail.get();
                    v.setModId(userId);
                    v.setIsDeleted(true);
                    wAddressEmailRepository.save(v);
                }

                //그룹 관계 삭제
                List<AddressGroup> addressGroupList = wAddressGroupRepository.findByUserAddressId(userAddressId);
                addressGroupList.stream().forEach(ag->{
                    AddressGroup addressGroup = ag;
                    addressGroup.setModId(userId);
                    addressGroup.setIsDeleted(true);
                    wAddressGroupRepository.save(addressGroup);
                });

            }

        });

    }





}
