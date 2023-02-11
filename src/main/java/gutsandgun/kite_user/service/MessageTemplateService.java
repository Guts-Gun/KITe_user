package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.PageResultDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.entity.write.MessageTemplate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageTemplateService {

    List<MessageTemplateDto> getAllUserMessageTemplateList(String userId);  // 메시지 템플릿 리스트 조회

    Page<MessageTemplateDto> getUserMessageTemplateList(String userId, PageRequestDTO requestDTO);  // 메시지 템플릿 페이징 리스트 조회

    Long insertMessageTemplate(String userId, MessageTemplateDto messageTemplateDto); // 메시지 템플릿 추가

    void deleteMessageTemplate(String userId, List<Long> messageTemplateList); // 메시지 템플릿 일괄 삭제

}