package gutsandgun.kite_user.entity.write;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import gutsandgun.kite_sendmanager.entity.BaseTimeEntity;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE user_phone SET is_deleted=true WHERE id = ?")
@Table(name="user_phone")
@NoArgsConstructor
@ToString
public class UserPhone extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    @Comment("user id")
    private Long userId;

    @Comment("발신 이름")
    private String name;
    @Comment("발신 전화번호")
    private String phone;

    @ColumnDefault("false")
    private Boolean isDeleted = false;


    @Comment("생성자")
    @Column(name = "reg_id", nullable = false, length = 20)
    private Long regId;

    @Comment("수정자")
    @Column(name = "mod_id", length = 20)
    private Long ModId;

    @Builder
    public UserPhone(Long userId, String name,String phone){
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.regId = userId;
    }
}
