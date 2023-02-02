package gutsandgun.kite_user.entity.read;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE sending SET is_deleted=true WHERE id = ?")
@Table(name="sending")
public class Sending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    private Long userId;

    private String ruleType;

    private String sendingType;

    private Long totalSending;

    private Long requestTime;

    private Long scheduleTime;

    private String title;

    private String media_link;
    
    private String content;

    @Comment("발신자")
    private String sender;

    @ColumnDefault("false")
    private Boolean isDeleted = false;

    @Comment("생성자")
    @Column(name = "reg_id", nullable = false, length = 20)
    private String regId;

    @Comment("수정자")
    @Column(name = "mod_id", length = 20)
    private String ModId;

    @Comment("생성일자")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Comment("수정일자")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    private Boolean isDeleted = false;
}
