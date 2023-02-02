package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.group.GroupDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDetailDto;
import gutsandgun.kite_user.dto.group.ResponseGroupDto;
import gutsandgun.kite_user.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    //read
    @GetMapping("")
    public List<ResponseGroupDto> getGroupList(Principal principal){
        String userId = getUser(principal);

        return(groupService.getUserGroupList(userId));
    }

    @GetMapping("/{groupId}")
    public ResponseGroupDetailDto getGroup(Principal principal,@PathVariable Long groupId){
        String userId = getUser(principal);

        return(groupService.getUserGroupById(userId,groupId));
    }

    @PostMapping("/create")
    public Long createGroup(Principal principal,@RequestBody GroupDto groupDto){
        String userId = getUser(principal);

        return(groupService.createUserGroup(userId,groupDto));
    }

    //create
    @PostMapping("/copy")
    public Long copyGroup(Principal principal,@RequestBody GroupDto groupDto){
        String userId = getUser(principal);

        return(groupService.copyUserGroup(userId,groupDto));
    }

    //update
    @PutMapping("")
    public Long changeGroup(Principal principal,@RequestBody GroupDto groupDto){
        String userId = getUser(principal);

        return(groupService.changeUserGroup(userId,groupDto));
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(Principal principal,@PathVariable Long groupId){
        String userId = getUser(principal);

        groupService.deleteUserGroup(userId,groupId);
        return("?");
    }


    @DeleteMapping("/list")
    public void deleteGroupList(Principal principal,@RequestBody List<Long> groupIdList){
        String userId = getUser(principal);

        groupService.deleteUserGroupList(userId,groupIdList);
    }




    @PostMapping("/address/{groupId}")
    public Long createAddressGroup(Principal principal,@PathVariable Long groupId,@RequestBody List<Long> addressIdList){
        String userId = getUser(principal);

        return(groupService.createAddressGroup(userId,groupId,addressIdList));
    }



    @DeleteMapping("/address/{groupId}")
    public Long deleteAddressGroup(Principal principal,@PathVariable Long groupId,@RequestBody List<Long> addressIdList){
        String userId = getUser(principal);

        return(groupService.deleteAddressGroup(userId,groupId,addressIdList));
    }

    public String getUser(Principal principal){
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userId = token.getTokenAttributes().get("preferred_username").toString();

        System.out.printf("getUser: ");
        System.out.println(userId);

        return userId;
    }

}
