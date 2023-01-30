package gutsandgun.kite_user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "S000", "잘못된 요청입니다."),
    UN_AUTHORIZATION(401, "S001", "토큰값이 없습니다");


    private int status;
    private String code;
    private String message;

}
