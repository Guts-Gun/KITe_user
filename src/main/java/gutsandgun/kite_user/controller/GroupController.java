package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.group.GroupDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDetailDto;
import gutsandgun.kite_user.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    //read
    @GetMapping("")
    public List<GroupDto> getGroupList(){
        Long userId = 1L;
        return(groupService.getUserGroupList(userId));
    }

    @GetMapping("/{groupId}")
    public ResponseGroupDetailDto getGroup(@PathVariable Long groupId){
        Long userId = 1L;
        return(groupService.getUserGroupById(userId,groupId));
    }

    @PostMapping("/create")
    public Long createGroup(@RequestBody GroupDto groupDto){
        Long userId = 1L;
        return(groupService.createUserGroup(userId,groupDto));
    }

    //create
    @PostMapping("/copy")
    public Long copyGroup(@RequestBody GroupDto groupDto){
        Long userId = 1L;
        return(groupService.copyUserGroup(userId,groupDto));
    }

    //update
    @PutMapping("")
    public Long changeGroup(@RequestBody GroupDto groupDto){
        Long userId = 1L;
        return(groupService.changeUserGroup(userId,groupDto));
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Long groupId){
        Long userId = 1L;
        groupService.deleteUserGroup(userId,groupId);
        return("?");
    }


    @DeleteMapping("/list")
    public void deleteGroupList(@RequestBody List<Long> groupIdList){
        Long userId = 1L;
        groupService.deleteUserGroupList(userId,groupIdList);
    }




    @PostMapping("/address/{groupId}")
    public Long createAddressGroup(@PathVariable Long groupId,@RequestBody List<Long> addressIdList){
        Long userId = 1L;
        return(groupService.createAddressGroup(userId,groupId,addressIdList));
    }



    @DeleteMapping("/address/{groupId}")
    public Long deleteAddressGroup(@PathVariable Long groupId,@RequestBody List<Long> addressIdList){
        Long userId = 1L;
        return(groupService.deleteAddressGroup(userId,groupId,addressIdList));
    }



}
