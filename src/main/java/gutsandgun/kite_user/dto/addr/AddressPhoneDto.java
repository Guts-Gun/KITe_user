package gutsandgun.kite_user.dto.addr;

import gutsandgun.kite_user.entity.write.AddressPhone;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link AddressPhone} entity
 */
@Data
public class AddressPhoneDto implements Serializable {
    private final Long id;
    private final String userAddressId;
    private final String phone;
    private final Boolean isDeleted;
}