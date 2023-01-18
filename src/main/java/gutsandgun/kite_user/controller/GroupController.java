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
        Long userId = 1L;
        return(groupService.getUserGroupList(userId));
    }


    //그룹 내 변경
    /*
    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId){
        Long userId = 1L;
        return(groupService.getUserGroupById(groupId));
    }
     */

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

    //delete -> body로 처리
    @DeleteMapping("/")
    public String deleteGroupList(@RequestBody String value){
        return("");
    }


    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Long groupId){
        Long userId = 1L;
        groupService.deleteUserGroup(userId,groupId);
        return("?");
    }
}
