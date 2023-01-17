package gutsandgun.kite_user.dto.user;

import gutsandgun.kite_user.entity.write.UserPhone;
import jakarta.persistence.Convert;
import lombok.*;


/**
 * A DTO for the {@link UserPhone} entity
 */
@Data
@AllArgsConstructor
public class UserPhoneDto {
    private Long id;
    private Long userId;
    private String name;
    private String phone;

    @Builder
    public UserPhoneDto(UserPhone userPhone){
        this.id = userPhone.getId();
        this.userId = userPhone.getUserId();
        this.name = userPhone.getName();
        this.phone = userPhone.getPhone();
    }

     public UserPhone toEntity(){
        return new UserPhone(this.userId,this.name,this.phone);
    }
}