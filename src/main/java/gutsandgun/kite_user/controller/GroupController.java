package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.group.GroupDto;
import gutsandgun.kite_user.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @Autowired
    GroupService groupService;


    //read
    @GetMapping("")
    public List<GroupDto> getGroupList(){
        return(groupService.getUserGroupList());
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId){
        return(groupService.getUserGroupById(groupId));
    }


    @PostMapping("")
    public Long makeGroup(@RequestBody GroupDto groupDto){
        return(groupService.createUserGroup(groupDto));
    }

    //create
    @PostMapping("/{groupId}")
    public Long copyGroup(@PathVariable Long groupId){
        return(groupService.copyUserGroup(groupId));
    }

    //update
    @PutMapping("/{groupId}")
    public Long changeGroup(@PathVariable Long groupId,@RequestBody GroupDto groupDto){
        return(groupService.changeUserGroup(groupId,groupDto));
    }

    //delete
    @DeleteMapping("/")
    public String deleteGroupList(){
        return("");
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Long groupId){
        groupService.deleteUserGroup(groupId);
        return("?");
    }
}
