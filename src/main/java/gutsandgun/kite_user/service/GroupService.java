package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.group.GroupDto;
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
    //group
    private final ReadUserGroupRepository readUserGroupRepository;
    private final WriteUserGroupRepository wUserGroupRepository;
    private final WriteAddressGroupRepository wAddressGroupRepository;
    private final WriteUserAddressRepository wUserAddressRepository;


    public List<GroupDto> getUserGroupList(Long userId){
        return wUserGroupRepository.findByUserId(userId).stream().map(m -> new GroupDto(m)).collect(Collectors.toList());
    }
    public GroupDto getUserGroupById(Long id){
        return new GroupDto(wUserGroupRepository.findById(id).get());
    }

    public Long createUserGroup(Long userId,GroupDto groupDto){
        Optional<UserGroup> check = wUserGroupRepository.findByUserIdAndGroupName(userId,groupDto.getGroupName());
        if(!check.isPresent()){
            UserGroup userGroup = groupDto.toEntity(userId,groupDto.getGroupName());
            UserGroup userGroupSave = wUserGroupRepository.save(userGroup);
            return userGroupSave.getId();
        }
        return null;
    }

    public Long copyUserGroup(Long userId,GroupDto groupDto){
        //그룹 복사
        Optional<UserGroup> idCheck = wUserGroupRepository.findByIdAndUserId(groupDto.getId(),userId);
        Optional<UserGroup> nameCheck = wUserGroupRepository.findByUserIdAndGroupName(userId,groupDto.getGroupName());
        if(idCheck.isPresent() && !nameCheck.isPresent()){
            UserGroup copyGroup = UserGroup.builder()
                    .userId(idCheck.get().getUserId())
                    .groupName(groupDto.getGroupName())
                    .build();
            UserGroup userGroupSave = wUserGroupRepository.save(copyGroup);
            //전화번호부 복사
            List<AddressGroup> addressGroupList = wAddressGroupRepository.findByUserGroupId(groupDto.getId());
            addressGroupList.stream().forEach((d)->{
                AddressGroup addressGroup = AddressGroup.builder()
                        .userAddressId(d.getUserAddressId())
                        .userGroupId(userGroupSave.getId())
                        .build();
                wAddressGroupRepository.save(addressGroup);
            });
            return userGroupSave.getId();
        }
        return null;
    }

    public Long changeUserGroup(Long userId,GroupDto groupDto){
        Optional<UserGroup> idCheck = wUserGroupRepository.findByIdAndUserId(groupDto.getId(),userId);
        Optional<UserGroup> nameCheck = wUserGroupRepository.findByUserIdAndGroupName(userId,groupDto.getGroupName());
        if(idCheck.isPresent() && !nameCheck.isPresent()){
            UserGroup userGroup = idCheck.get();
            userGroup.setGroupName(groupDto.getGroupName());
            UserGroup userGroupSave = wUserGroupRepository.save(userGroup);
            return userGroupSave.getId();
        }
        return null;
    }

    public void deleteUserGroupList(){

    }

    public Long deleteUserGroup(Long userId,Long groupId){
        Optional<UserGroup> idCheck = wUserGroupRepository.findByIdAndUserId(groupId,userId);
        List<AddressGroup> addressGroupCheck = wAddressGroupRepository.findByUserGroupId(groupId);
        if(idCheck.isPresent()){
            //그룹 삭제
            UserGroup userGroup = idCheck.get();
            userGroup.setIsDeleted(true);
            UserGroup saveUserGroup = wUserGroupRepository.save(userGroup);

            //그룹 관계 삭제
            addressGroupCheck.stream().forEach(c->{
                AddressGroup addressGroup = c;
                addressGroup.setIsDeleted(true);
                AddressGroup saveAddressGroup = wAddressGroupRepository.save(addressGroup);
            });

            return saveUserGroup.getId();
        }
        return null;
    }



}
