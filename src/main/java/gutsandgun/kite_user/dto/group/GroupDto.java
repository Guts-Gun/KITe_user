package gutsandgun.kite_user.dto.group;

import gutsandgun.kite_user.entity.write.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class GroupDto {
    private Long id;
    private Long userId;
    private String groupName;

    public GroupDto(UserGroup userGroup){
        this.id = userGroup.getId();
        this.userId = userGroup.getUserId();
        this.groupName = userGroup.getGroupName();
    }

    public UserGroup toEntity(Long userId,String groupName){
        return UserGroup.builder()
                .userId(userId)
                .groupName(groupName)
                .build();
    }

}
