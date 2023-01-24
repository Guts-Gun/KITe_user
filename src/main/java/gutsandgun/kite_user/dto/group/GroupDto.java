package gutsandgun.kite_user.dto.group;

import gutsandgun.kite_user.entity.write.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class GroupDto {
    private Long id;
    private Long userId;
    private String groupName;

    private LocalDateTime regDt;
    private LocalDateTime modDt;

    public GroupDto(UserGroup userGroup){
        this.id = userGroup.getId();
        this.userId = userGroup.getUserId();
        this.groupName = userGroup.getGroupName();
        this.regDt = userGroup.getRegDt();
        this.modDt = userGroup.getModDt();
    }

    public UserGroup toEntity(Long userId,String groupName){
        return UserGroup.builder()
                .userId(userId)
                .groupName(groupName)
                .build();
    }
}
