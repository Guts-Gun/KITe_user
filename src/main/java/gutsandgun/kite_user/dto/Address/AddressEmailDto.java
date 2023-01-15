package gutsandgun.kite_user.dto.Address;

import gutsandgun.kite_user.entity.write.AddressEmail;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link AddressEmail} entity
 */
@Data
public class AddressEmailDto implements Serializable {
    private final Long id;
    private final Long userAddressId;
    private final String email;
    private final Boolean isDeleted;
}