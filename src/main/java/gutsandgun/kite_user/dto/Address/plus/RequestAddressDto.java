package gutsandgun.kite_user.dto.Address.plus;

import gutsandgun.kite_user.entity.write.AddressEmail;
import gutsandgun.kite_user.entity.write.AddressPhone;
import gutsandgun.kite_user.entity.write.UserAddress;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Setter
public class RequestAddressDto {
    private Long userId;
    private Long groupId;
    private String name;
    private String phone;
    private String email;

    public UserAddress toUserAddressEntity() {
        return UserAddress.builder()
                .userId(this.userId)
                .name(this.name)
                .build();
    }
    public AddressPhone toUserAddressPhoneEntity(Long userAddressId){
        return AddressPhone.builder()
                .userAddressId(userAddressId)
                .phone(this.phone).build();
    }

    public AddressEmail toUserAddressEmailEntity(Long userAddressId){
        return AddressEmail.builder()
                .userAddressId(userAddressId)
                .email(this.email).build();
    }

}



