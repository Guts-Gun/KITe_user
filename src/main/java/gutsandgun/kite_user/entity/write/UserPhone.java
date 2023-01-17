package gutsandgun.kite_user.entity.write;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE user_phone SET is_deleted=true WHERE id = ?")
@Table(name="user_phone")
@NoArgsConstructor
@ToString
public class UserPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    private Long userId;

    private String name;
    private String phone;

    private Boolean isDeleted = false;


    @Builder
    public UserPhone(Long userId, String name,String phone){
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }
}
