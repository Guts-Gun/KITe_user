package gutsandgun.kite_user.dto.user;

import gutsandgun.kite_user.entity.write.UserEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserEmail} entity
 */
@Data
@AllArgsConstructor
public class UserEmailDto{
    private Long id;
    private String userId;
    private String name;
    private String email;

    @Builder
    public UserEmailDto(UserEmail userEmail){
        this.id = userEmail.getId();
        this.userId = userEmail.getUserId();
        this.name = userEmail.getName();
        this.email = userEmail.getEmail();
    }

    public UserEmail toEntity() {return new UserEmail(this.userId,this.name,this.email);}
}