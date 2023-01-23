package gutsandgun.kite_user.dto.addr;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ResponseAddressWithGroupDto {
    Long userAddressId;
    String name;
    String email;
    String phone;
    List<ResponseBelongGroupDto> groupList;


    public ResponseAddressWithGroupDto(ResponseAddressDto responseAddressDto, List<ResponseBelongGroupDto> responseBelongGroupDtoList) {
        this.userAddressId = responseAddressDto.getUserAddressId();
        this.name = responseAddressDto.getName();
        this.email = responseAddressDto.getEmail();
        this.phone = responseAddressDto.getPhone();

        this.groupList = responseBelongGroupDtoList;
    }
}
