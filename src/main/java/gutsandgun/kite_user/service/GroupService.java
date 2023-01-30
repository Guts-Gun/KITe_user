package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.addr.ResponseAddressDto;
import gutsandgun.kite_user.dto.group.GroupDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDetailDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDto;
import gutsandgun.kite_user.entity.write.AddressGroup;
import gutsandgun.kite_user.entity.write.UserAddress;
import gutsandgun.kite_user.entity.write.UserGroup;
import gutsandgun.kite_user.repository.read.*;
import gutsandgun.kite_user.repository.write.*;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {
    private final AddressService addressService;

    //group
    private final ReadUserGroupRepository readUserGroupRepository;
    private final WriteUserGroupRepository wUserGroupRepository;
    private final WriteAddressGroupRepository wAddressGroupRepository;
    private final WriteUserAddressRepository wUserAddressRepository;


    public List<ResponseGroupDto> getUserGroupList(String userId){
        return wUserGroupRepository.findByUserId(userId).stream().map(m -> {
            Long addressCount = wAddressGroupRepository.countByUserGroupId( m.getId());
            return new ResponseGroupDto(m,addressCount);
        }).collect(Collectors.toList());



    }
    public ResponseGroupDetailDto getUserGroupById(String userId, Long groupId){
        //그룹 정보
        Optional<UserGroup> check = wUserGroupRepository.findByIdAndUserId(groupId,userId);
        if(check.isPresent()){
            //그룹 내 전화번호 정보
            UserGroup userGroup = check.get();
            List<AddressGroup> addressGroup = wAddressGroupRepository.findByUserGroupId(userGroup.getId());
            List<ResponseAddressDto> addressList = addressGroup.stream().map(d -> (addressService.getResponseAddressDto(d.getUserAddressId()))).collect(Collectors.toList());
            return(new ResponseGroupDetailDto(userGroup,addressList));
        }
        return null;
    }

    public Long createUserGroup(String userId,GroupDto groupDto){
        Optional<UserGroup> check = wUserGroupRepository.findByUserIdAndGroupName(userId,groupDto.getGroupName());
        if(!check.isPresent()){
            UserGroup userGroup = groupDto.toEntity(userId);
            UserGroup userGroupSave = wUserGroupRepository.save(userGroup);
            return userGroupSave.getId();
        }
        return null;
    }

    public Long copyUserGroup(String userId,GroupDto groupDto){
        //그룹 복사
        Optional<UserGroup> idCheck = wUserGroupRepository.findByIdAndUserId(groupDto.getId(),userId);
        Optional<UserGroup> nameCheck = wUserGroupRepository.findByUserIdAndGroupName(userId,groupDto.getGroupName());
        if(idCheck.isPresent() && !nameCheck.isPresent()){
            UserGroup copyGroup = UserGroup.builder()
                    .userId(idCheck.get().getUserId())
                    .groupName(groupDto.getGroupName())
                    .description(groupDto.getDescription())
                    .build();
            UserGroup userGroupSave = wUserGroupRepository.save(copyGroup);
            //전화번호부 복사
            List<AddressGroup> addressGroupList = wAddressGroupRepository.findByUserGroupId(groupDto.getId());
            addressGroupList.stream().forEach((d)->{
                AddressGroup addressGroup = AddressGroup.builder()
                        .userAddressId(d.getUserAddressId())
                        .userGroupId(userGroupSave.getId())
                        .regId(userId)
                        .build();
                wAddressGroupRepository.save(addressGroup);
            });
            return userGroupSave.getId();
        }
        return null;
    }

    public Long changeUserGroup(String userId,GroupDto groupDto){
        Optional<UserGroup> idCheck = wUserGroupRepository.findByIdAndUserId(groupDto.getId(),userId);
        Optional<UserGroup> nameCheck = wUserGroupRepository.findByUserIdAndGroupName(userId,groupDto.getGroupName());
        if(idCheck.isPresent() && !nameCheck.isPresent()){
            UserGroup userGroup = idCheck.get();
            userGroup.setGroupName(groupDto.getGroupName());
            userGroup.setDescription(groupDto.getDescription());
            userGroup.setModId(userId);
            UserGroup userGroupSave = wUserGroupRepository.save(userGroup);
            return userGroupSave.getId();
        }
        return null;
    }


    public Long deleteUserGroup(String userId,Long groupId){
        Optional<UserGroup> idCheck = wUserGroupRepository.findByIdAndUserId(groupId,userId);
        List<AddressGroup> addressGroupCheck = wAddressGroupRepository.findByUserGroupId(groupId);
        if(idCheck.isPresent()){
            //그룹 삭제
            UserGroup userGroup = idCheck.get();
            userGroup.setModId(userId);
            userGroup.setIsDeleted(true);
            UserGroup saveUserGroup = wUserGroupRepository.save(userGroup);

            //그룹 관계 삭제
            addressGroupCheck.stream().forEach(c->{
                AddressGroup addressGroup = c;
                addressGroup.setModId(userId);
                addressGroup.setIsDeleted(true);
                AddressGroup saveAddressGroup = wAddressGroupRepository.save(addressGroup);
            });

            return saveUserGroup.getId();
        }
        return null;
    }

    public void deleteUserGroupList(String userId, List<Long> groupIdList){
        groupIdList.stream().forEach(d->{
            deleteUserGroup(userId,d);
        });
    }


    public Long createAddressGroup(String userId,Long groupId,List<Long> addressList) {
        Optional<UserGroup> check = wUserGroupRepository.findByIdAndUserId(groupId,userId);
        if(check.isPresent()){
            //그룹 내 전화번호 추가
            addressList.stream().forEach(d->{
                Optional<UserAddress> checkAddress = wUserAddressRepository.findById(d);
                if(checkAddress.isPresent()){
                    Optional<AddressGroup> checkAddressGroup = wAddressGroupRepository.findByUserAddressIdAndUserGroupId(d,groupId);
                    if(!checkAddressGroup.isPresent()){
                        AddressGroup addressGroup = AddressGroup.builder()
                                .userGroupId(groupId)
                                .userAddressId(d)
                                .regId(userId)
                                .build();
                        wAddressGroupRepository.save(addressGroup);
                    }
                }
            });
            return groupId;
        }
        return null;
    }

    public Long deleteAddressGroup(String userId,Long groupId,List<Long> addressList){
        Optional<UserGroup> check = wUserGroupRepository.findByIdAndUserId(groupId,userId);
        if(check.isPresent()){
            //그룹 내 전화번호 삭제
            addressList.stream().forEach(d->{
                Optional<AddressGroup> checkAddressGroup = wAddressGroupRepository.findByUserAddressIdAndUserGroupId(d,groupId);
                if(checkAddressGroup.isPresent()){
                    AddressGroup addressGroup = checkAddressGroup.get();
                    addressGroup.setModId(userId);
                    addressGroup.setIsDeleted(true);
                    wAddressGroupRepository.save(addressGroup);
                }
            });
            return groupId;
        }
        return null;
    }

}
