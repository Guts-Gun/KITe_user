package gutsandgun.kite_user.dto.address;

import gutsandgun.kite_user.entity.write.UserAddress;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserAddress} entity
 */
@Data
public class UserAddressDto implements Serializable {
    private final Long id;
    private final Long userId;
    private final String name;
    private final Boolean isDeleted;


}