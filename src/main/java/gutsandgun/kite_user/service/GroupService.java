package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.Group.GroupDto;
import gutsandgun.kite_user.entity.write.UserGroup;
import gutsandgun.kite_user.repository.read.*;
import gutsandgun.kite_user.repository.write.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {
    //group
    private final ReadUserGroupRepository readUserGroupRepository;
    private final WriteUserGroupRepository writeUserGroupRepository;


    public List<GroupDto> getUserGroupList(){
        return writeUserGroupRepository.findAll().stream().map(m -> new GroupDto(m)).collect(Collectors.toList());
    }
    public GroupDto getUserGroupById(Long id){
        return new GroupDto(writeUserGroupRepository.findById(id).get());
    }

    public Long createUserGroup(GroupDto groupDto){
        UserGroup userGroup = groupDto.toEntity(groupDto.getUserId(),groupDto.getGroupName());
        UserGroup userGroupSave = writeUserGroupRepository.save(userGroup);
        return userGroupSave.getId();
    }
    //예외처리
    public Long copyUserGroup(Long id){
        UserGroup userGroup = writeUserGroupRepository.findById(id).get();
        UserGroup copyGroup = userGroup.builder()
                .userId(userGroup.getUserId())
                .groupName(userGroup.getGroupName())
                .build();
        UserGroup userGroupSave = writeUserGroupRepository.save(copyGroup);
        return userGroupSave.getId();
    }

    public Long changeUserGroup(Long id,GroupDto dto){
        UserGroup userGroup = writeUserGroupRepository.findById(id).get();
        userGroup.setGroupName(dto.getGroupName());
        UserGroup userGroupSave = writeUserGroupRepository.save(userGroup);
        return userGroupSave.getId();
    }

    public void deleteUserGroupList(){

    }

    public void deleteUserGroup(Long id){
        UserGroup userGroup = writeUserGroupRepository.findById(id).get();
        writeUserGroupRepository.deleteById(id);
    }



}
