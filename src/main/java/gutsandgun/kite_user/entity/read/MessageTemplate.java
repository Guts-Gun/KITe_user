package gutsandgun.kite_user.entity.read;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@SQLDelete(sql= "UPDATE message_template SET is_deleted=true WHERE id = ?")
@Table(name="message_template")
@NoArgsConstructor
@AllArgsConstructor
public class MessageTemplate{

    @Id
    @Column(name = "id")

    private Long id;

    @Column(name = "fk_user_id")
    private String userId;

    private String title;

    private String content;

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


}
