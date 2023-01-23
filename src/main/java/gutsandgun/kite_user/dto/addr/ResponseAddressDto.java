package gutsandgun.kite_user.dto.addr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ResponseAddressDto {
    Long userAddressId;
    String name;
    String email;
    String phone;
}
