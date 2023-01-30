package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.PageResultDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.entity.write.MessageTemplate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageTemplateService {

    Page<MessageTemplateDto> getUserMessageTemplateList(String userId, PageRequestDTO requestDTO);

    Long insertMessageTemplate(String userId, MessageTemplateDto messageTemplateDto);

    default MessageTemplate dtoToEntity(MessageTemplateDto dto, String userId) {
        MessageTemplate messageTemplate = MessageTemplate.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userId(userId)
                .regId(userId)
                .build();
        return messageTemplate;
    }
}