package gutsandgun.kite_user.entity.write;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE user SET is_deleted=true WHERE id = ?")
@Table(name="user")
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private Boolean isDeleted = false;
}
