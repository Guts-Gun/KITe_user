package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.group.GroupDto;
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
        return(groupService.getUserGroupList());
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId){
        return(groupService.getUserGroupById(groupId));
    }

    @PostMapping("/create")
    public Long createGroup(@RequestBody GroupDto groupDto){
        return(groupService.createUserGroup(groupDto));
    }

    //create
    @PostMapping("/copy")
    public Long copyGroup(@RequestBody GroupDto groupDto){
        return(groupService.copyUserGroup(groupDto));
    }

    //update
    @PutMapping("")
    public Long changeGroup(@RequestBody GroupDto groupDto){
        return(groupService.changeUserGroup(groupDto));
    }

    //delete -> body로 처리
    @DeleteMapping("/")
    public String deleteGroupList(@RequestBody String value){
        return("");
    }


    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Long groupId){
        groupService.deleteUserGroup(groupId);
        return("?");
    }
}
