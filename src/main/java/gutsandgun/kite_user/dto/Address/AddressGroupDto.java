package gutsandgun.kite_user.dto.Address;

import gutsandgun.kite_user.entity.write.AddressGroup;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link AddressGroup} entity
 */
@Data
public class AddressGroupDto implements Serializable {
    private final Long id;
    private final Long userAddressId;
    private final Long userGroupId;
    private final Boolean isDeleted;
}