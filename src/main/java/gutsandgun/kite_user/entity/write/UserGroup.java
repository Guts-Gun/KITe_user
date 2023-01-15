package gutsandgun.kite_user.entity.write;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE user_group SET is_deleted=true WHERE id = ?")
@Table(name="user_group")
@NoArgsConstructor
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    private Long userId;

    private String groupName;

    private Boolean isDeleted = false;


    @Builder
    public UserGroup(Long userId, String groupName){
        this.userId = userId;
        this.groupName = groupName;
    }


}
