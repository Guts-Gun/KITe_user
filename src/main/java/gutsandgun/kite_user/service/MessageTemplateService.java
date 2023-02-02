package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.PageResultDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.entity.write.MessageTemplate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageTemplateService {

    Page<MessageTemplateDto> getUserMessageTemplateList(String userId, PageRequestDTO requestDTO);  // 메시지 템플릿 페이징 리스트 조회

    Long insertMessageTemplate(String userId, MessageTemplateDto messageTemplateDto); // 메시지 템플릿 추가

    void deleteMessageTemplate(String userId, List<Long> messageTemplateList); // 메시지 템플릿 일괄 삭제



    default MessageTemplate dtoToEntity(MessageTemplateDto dto, String userId) {
        MessageTemplate messageTemplate = MessageTemplate.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userId(userId)
                .regId(userId)
                .isDeleted(false)
                .build();
        return messageTemplate;
    }
}