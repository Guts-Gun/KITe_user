package gutsandgun.kite_user.dto.group;

import gutsandgun.kite_user.dto.addr.ResponseAddressDto;
import gutsandgun.kite_user.entity.write.UserGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class ResponseGroupDto {
    private Long id;
    private String userId;
    private String groupName;
    private String description;

    private LocalDateTime regDt;
    private LocalDateTime modDt;

    private Long addressCount;

    public ResponseGroupDto(UserGroup userGroup,Long addressCount) {
        this.id = userGroup.getId();
        this.userId = userGroup.getUserId();
        this.groupName = userGroup.getGroupName();
        this.description =userGroup.getDescription();

        this.regDt = userGroup.getRegDt();
        this.modDt = userGroup.getModDt();

        this.addressCount = Long.valueOf(addressCount);
    }
}
