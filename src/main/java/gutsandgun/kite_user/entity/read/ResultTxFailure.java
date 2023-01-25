package gutsandgun.kite_user.entity.read;

import gutsandgun.kite_user.type.FailReason;
import gutsandgun.kite_user.type.SendingType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@SQLDelete(sql = "UPDATE result_tx_failure SET is_deleted=true WHERE id = ?")
public class ResultTxFailure {

	/**
	 * result tx id 1대 1 대응
	 */
	@Id
//    @Column(name = "pk_result_tx_id") comment 달면 굳이 이렇게 안해도 될듯?
	@Comment("result_tx id")
	private Long id;

	/**
	 * user id
	 */
	@Comment("user id")
	@Column(name = "fk_user_id")
	private String userId;

	/**
	 * sending 결과의 id
	 */
	@Comment("sending 결과의 id")
	@Column(name = "fk_result_sending_id")
	private Long resultSendingId;

	/**
	 * 메시지 TX의 id
	 */
	@Comment("메시지 TX ID")
	@Column(name = "fk_tx_id")
	private Long txId;


	/**
	 * 중계사 id
	 */
	@Comment("중계사 id")
	@Column(name = "fk_broker_id")
	private Long brokerId;

	/**
	 * 이거 머임 알려주셈
	 */
	@Comment("")
	@Column(name = "fk_sending_x_id")
	private Long sendingXId;

	/**
	 * 발송 메시지 타입
	 */
	@Comment("발송 메시지 타입")
	private SendingType sendingType;

	/**
	 * 실패 원인
	 */
	@Comment("실패 원인")
	private FailReason failReason;

	/**
	 * 메시지 제목
	 */
	@Comment("제목")
	private String title;

	/**
	 * 미디어 호스팅 주소
	 */
	@Comment("미디어 호스팅 주소")
	private String media_link;

	/**
	 * 메시지 내용
	 */
	@Comment("메시지 내용")
	private String content;

    @ColumnDefault("false")
	private Boolean isDeleted = false;
}
