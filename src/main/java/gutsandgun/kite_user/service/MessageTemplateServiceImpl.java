package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.entity.read.MessageTemplate;
import gutsandgun.kite_user.repository.read.ReadMessageTemplateRepository;
import gutsandgun.kite_user.repository.write.WriteMessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl implements MessageTemplateService {

    @Autowired
    private final WriteMessageTemplateRepository writeMessageTemplateRepository;

    @Autowired
    private final ReadMessageTemplateRepository readMessageTemplateRepository;

    @Autowired
    private final ModelMapper mapper;


    @Override
    public List<MessageTemplateDto> getAllUserMessageTemplateList(String userId) {

        List<MessageTemplate> messageTemplateList = readMessageTemplateRepository.findByUserId(userId);
        List<MessageTemplateDto> messageTemplateDtoList = new ArrayList<>();
        messageTemplateList.forEach(messageTemplate -> {
            messageTemplateDtoList.add(mapper.map(messageTemplate, MessageTemplateDto.class));
        });
        return messageTemplateDtoList;
    }

    @Override
    public Page<MessageTemplateDto> getUserMessageTemplateList(String userId, PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("regDt").descending());

        Page<MessageTemplate> messageTemplateList = readMessageTemplateRepository.findByUserId(userId, pageable);
        Page<MessageTemplateDto> resultSendingDtoList = messageTemplateList.map(messageTemplate -> mapper.map(messageTemplate, MessageTemplateDto.class));
        return resultSendingDtoList;
    }

    @Override
    public Long insertMessageTemplate(String userId, MessageTemplateDto messageTemplateDto){
        messageTemplateDto.setUserId(userId);
        messageTemplateDto.setRegId(userId);
        gutsandgun.kite_user.entity.write.MessageTemplate messageTemplate = writeMessageTemplateRepository.save(mapper.map(messageTemplateDto, gutsandgun.kite_user.entity.write.MessageTemplate.class));
        return messageTemplate.getId();
    }

    @Override
    public void deleteMessageTemplate(String userId, List<Long> messageTemplateList) {
        messageTemplateList.forEach(messageTemplateId -> {
            writeMessageTemplateRepository.deleteByIdAndUserId(messageTemplateId, userId);
        });
    }


}