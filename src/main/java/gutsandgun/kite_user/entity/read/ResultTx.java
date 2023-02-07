package gutsandgun.kite_user.entity.read;

import gutsandgun.kite_user.entity.BaseTimeEntity;
import gutsandgun.kite_user.type.FailReason;
import gutsandgun.kite_user.type.SendingType;
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
@SQLDelete(sql = "UPDATE result_tx SET is_deleted=true WHERE id = ?")
public class ResultTx extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * user id
	 */
	@Column(name = "fk_user_id")
	@Comment("user id")
	private String userId;

	/**
	 * sending 결과의 id
	 */
	@Column(name = "fk_result_sending_id")
	@Comment("sending 결과의 id")
	private Long resultSendingId;

	/**
	 * 메시지 TX의 id
	 */
	@Column(name = "fk_tx_id")
	@Comment("메시지 TX ID")
	private Long txId;


	/**
	 * 중계사 id
	 */
	@Column(name = "fk_broker_id")
	@Comment("중계사 id")
	private Long brokerId;


	/**
	 * 발송 메시지 타입
	 */
	@Comment("발송 메시지 타입")
	private SendingType sendingType;

	/**
	 * 발신자 주소
	 */
	@Comment("발신자 주소")
	private String sender;

	/**
	 * 수신자 주소
	 */
	@Comment("수신자 주소")
	private String receiver;

	/**
	 * 성공 여부
	 */
	@Comment("성공 여부")
	private Boolean success;

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
	private String mediaLink;

	/**
	 * 메시지 내용
	 */
	@Comment("메시지 내용")
	private String content;

	// 시간 관련 로그
	/**
	 * 입력 시간 unix time
	 */
	@Comment("입력 시간")
	private Long inputTime;

	/**
	 * 예약 시간 unix time
	 */
	@Comment("예약 시간")
	private Long scheduleTime;

	/**
	 * 큐 삽입 시간 unix time
	 */
	@Comment("큐 삽입 시간")
	private Long startTime;

	/**
	 * 중계사 전송 시간 unix time
	 */
	@Comment("중계사 전송 시간 시간")
	private Long sendTime;

	/**
	 * 중계사 응답 완료 시간 unix time
	 */
	@Comment("중계사 응답 완료 시간")
	private Long completeTime;

	/**
	 * 결과 기록 시간
	 */
	@Comment("결과 기록 시간")
	private Long logTime;

	@ColumnDefault("false")
	private Boolean isDeleted = false;
}
