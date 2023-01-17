package gutsandgun.kite_user.dto.addr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Getter
public class RequestAddressListDto {
    private Long groupId;
    private List<RequestAddressDto> requestAddressList;
}
