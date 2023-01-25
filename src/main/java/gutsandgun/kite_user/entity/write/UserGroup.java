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
@SQLDelete(sql= "UPDATE user_group SET is_deleted=true WHERE id = ?")
@Table(name="user_group")
@NoArgsConstructor
public class UserGroup extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    @Comment("user id")
    private String userId;

    @Comment("그룹 이름")
    private String groupName;

    @Comment("그룹 설명")
    private String description;

    @ColumnDefault("false")
    private Boolean isDeleted = false;


    @Comment("생성자")
    @Column(name = "reg_id", nullable = false, length = 20)
    private String regId;

    @Comment("수정자")
    @Column(name = "mod_id", length = 20)
    private String ModId;

    @Builder
    public UserGroup(String userId, String groupName,String description){
        this.userId = userId;
        this.groupName = groupName;
        this.description = description;
        this.regId = userId;
    }


}
