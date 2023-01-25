package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.group.GroupDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDetailDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDto;
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
    public List<ResponseGroupDto> getGroupList(){
        String userId = "solbitest";
        return(groupService.getUserGroupList(userId));
    }

    @GetMapping("/{groupId}")
    public ResponseGroupDetailDto getGroup(@PathVariable Long groupId){
        String userId = "solbitest";
        return(groupService.getUserGroupById(userId,groupId));
    }

    @PostMapping("/create")
    public Long createGroup(@RequestBody GroupDto groupDto){
        String userId = "solbitest";
        return(groupService.createUserGroup(userId,groupDto));
    }

    //create
    @PostMapping("/copy")
    public Long copyGroup(@RequestBody GroupDto groupDto){
        String userId = "solbitest";
        return(groupService.copyUserGroup(userId,groupDto));
    }

    //update
    @PutMapping("")
    public Long changeGroup(@RequestBody GroupDto groupDto){
        String userId = "solbitest";
        return(groupService.changeUserGroup(userId,groupDto));
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Long groupId){
        String userId = "solbitest";
        groupService.deleteUserGroup(userId,groupId);
        return("?");
    }


    @DeleteMapping("/list")
    public void deleteGroupList(@RequestBody List<Long> groupIdList){
        String userId = "solbitest";
        groupService.deleteUserGroupList(userId,groupIdList);
    }




    @PostMapping("/address/{groupId}")
    public Long createAddressGroup(@PathVariable Long groupId,@RequestBody List<Long> addressIdList){
        String userId = "solbitest";
        return(groupService.createAddressGroup(userId,groupId,addressIdList));
    }



    @DeleteMapping("/address/{groupId}")
    public Long deleteAddressGroup(@PathVariable Long groupId,@RequestBody List<Long> addressIdList){
        String userId = "solbitest";
        return(groupService.deleteAddressGroup(userId,groupId,addressIdList));
    }



}
