package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.user.UserSendingRuleDto;
import gutsandgun.kite_user.entity.read.SendingRule;
import gutsandgun.kite_user.exception.CustomException;
import gutsandgun.kite_user.exception.ErrorCode;
import gutsandgun.kite_user.service.UserSendingRuleService;
import gutsandgun.kite_user.type.SendingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/sendingRule")
@Log4j2
@RequiredArgsConstructor
public class UserSendingRuleController {

    private final UserSendingRuleService sendingRuleService;

    /**
     * 사용자 중계사 분배 비율 기본 조회
     *
     * @author solbiko
     * @param principal 로그인 객체
     * @return String success
     */
    @GetMapping("/info")
    public ResponseEntity<List<Map<String,Object>>> getUserSendingRule(Principal principal, @RequestParam SendingType sendingType) {
        return new ResponseEntity<>(sendingRuleService.getUserSendingRule(getUserId(principal), sendingType), HttpStatus.OK);
    }


    /**
     * 사용자 중계사 분배 비율 기본 설정
     *
     * @author solbiko
     * @param principal 로그인 객체
     * @param userSendingRuleDtoList 사용자 중계사 분배 비율 리스트
     * @return String success
     */
    @PostMapping("/reg")
    public ResponseEntity<String> insertUserSendingRule(Principal principal, @RequestBody List<UserSendingRuleDto> userSendingRuleDtoList) {
        sendingRuleService.upsertUserSendingRule(getUserId(principal), userSendingRuleDtoList);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    public String getUserId(Principal principal){
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        if(token == null){
            throw new CustomException(ErrorCode.UN_AUTHORIZATION);
        }
        return token.getTokenAttributes().get("preferred_username").toString();
    }

}
