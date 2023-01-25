package gutsandgun.kite_user.type;

public enum FailReason {
	/**
	 * 중계사 문제 ex) 중계사 지연, 수신거부
	 */
	BROKER,
	/**
	 * 시스템 문제 ex) tx 입력 실패, 윤재 부채질 문제
	 */
	SYSTEM,
	/**
	 * 사용자 문제 ex)
	 */
	USER
}
