package gutsandgun.kite_user.entity.write;

import gutsandgun.kite_user.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE user SET is_deleted=true WHERE id = ?")
@Table(name="user")
public class User {

    /**
     * String user id generate from keycloak
     */
    @Id
    private String id;

    /**
     * user 이름
     */
    @Comment("유저 이름")
    private String name;

    /**
     * user email
     */
    @Comment("e-mail")
    private String email;

    @ColumnDefault("false")
    private Boolean isDeleted = false;
}
