package gutsandgun.kite_user.dto.group;

import gutsandgun.kite_user.dto.addr.ResponseAddressDto;
import gutsandgun.kite_user.dto.addr.ResponseAddressWithGroupDto;
import gutsandgun.kite_user.entity.write.UserGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class ResponseGroupDetailDto {
    private Long id;
    private Long userId;
    private String groupName;
    private String description;

    private LocalDateTime regDt;
    private LocalDateTime modDt;

    private Long addressCount;
    private List<ResponseAddressDto> addressList;

    public ResponseGroupDetailDto(UserGroup userGroup, List<ResponseAddressDto> addressList) {
        this.id = userGroup.getId();
        this.userId = userGroup.getUserId();
        this.groupName = userGroup.getGroupName();
        this.description = userGroup.getDescription();

        this.regDt = userGroup.getRegDt();
        this.modDt = userGroup.getModDt();

        this.addressCount = Long.valueOf(addressList.size());
        this.addressList = addressList;
    }
}
