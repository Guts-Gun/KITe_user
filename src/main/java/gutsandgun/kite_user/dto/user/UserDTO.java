package gutsandgun.kite_user.dto.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    private Integer status;

    private UserRole.Userrole userRole;

    private String statusInfo;
}
