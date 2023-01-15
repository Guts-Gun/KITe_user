package gutsandgun.kite_user.dto.Address.plus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Getter
public class RequestAddressListDto{
    private Long userId;
    private Long groupId;
    private List<RequestAddressDto> requestAddressDtoList;
}
