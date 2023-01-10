package gutsandgun.kite_user.entity.read;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE log_total SET is_deleted=true WHERE id = ?")
@Table(name="log_total")
public class LogTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_id")
    private Long userId;

    @Column(name = "fk_log_sending_id")
    private Long logSendingId;

    @Column(name = "fk_broker_id")
    private Long brokerId;

    @Column(name = "fk_sending_x_id")
    private Long SendingXId;

    private String sendingType;

    private Long logTime;

    private String sender;

    private String receiver;

    private String success;

    private String failure_cause;

    private Boolean isDeleted = false;

}