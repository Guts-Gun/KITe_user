package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.dto.user.UserSendingRuleDto;
import gutsandgun.kite_user.entity.write.MessageTemplate;
import gutsandgun.kite_user.type.SendingType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserSendingRuleService {

    List<Map<String,Object>> getUserSendingRule(String userId, SendingType sendingType);    // 사용자 중계사 분배 비율 기본 조회

    void upsertUserSendingRule(String userId, List<UserSendingRuleDto> userSendingRuleDtoList); // 사용자 중계사 분배 비율 기본 설정

}