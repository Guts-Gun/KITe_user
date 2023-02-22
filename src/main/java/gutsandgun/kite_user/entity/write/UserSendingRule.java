package gutsandgun.kite_user.entity.write;

import gutsandgun.kite_user.entity.BaseTimeEntity;
import jakarta.persistence.*;
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
@SQLDelete(sql= "UPDATE user_sending_rule SET is_deleted=true WHERE id = ?")
@Table(name = "user_sending_rule",
        indexes = {
                @Index(name = "idx_user_sending_rule_user_id", columnList = "fk_user_id")
        })
public class UserSendingRule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    private String userId;

    @Column(name = "fk_broker_id")
    @Comment("중계사 id")
    private Long brokerId;

    @Comment("발송 비중")
    private Long weight;

    @Comment("생성자")
    @Column(name = "reg_id", nullable = false, length = 20)
    private String regId;

    @Comment("수정자")
    @Column(name = "mod_id", length = 20)
    private String modId;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
