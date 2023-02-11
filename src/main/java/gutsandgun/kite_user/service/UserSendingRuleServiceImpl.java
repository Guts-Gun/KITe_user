package gutsandgun.kite_user.service;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.dto.user.UserSendingRuleDto;
import gutsandgun.kite_user.entity.read.MessageTemplate;
import gutsandgun.kite_user.entity.read.UserSendingRule;
import gutsandgun.kite_user.repository.read.ReadMessageTemplateRepository;
import gutsandgun.kite_user.repository.read.ReadUserSendingRuleRepository;
import gutsandgun.kite_user.repository.write.WriteMessageTemplateRepository;
import gutsandgun.kite_user.repository.write.WriteUserSendingRuleRepository;
import gutsandgun.kite_user.type.SendingType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserSendingRuleServiceImpl implements UserSendingRuleService {

    @Autowired
    private final WriteUserSendingRuleRepository writeUserSendingRuleRepository;

    @Autowired
    private final ReadUserSendingRuleRepository readUserSendingRuleRepository;

    @Autowired
    private final ModelMapper mapper;


    @Override
    public List<Map<String,Object>> getUserSendingRule(String userId, SendingType sendingType) {
        int sendingType1 = sendingType.ordinal();
        List<Map<String,Object>> userSendingRulList = readUserSendingRuleRepository.findByUserId(userId, sendingType1);
        return userSendingRulList;
    }

    @Override
    public void upsertUserSendingRule(String userId, List<UserSendingRuleDto> userSendingRuleDtoList) {
        userSendingRuleDtoList.forEach(userSendingRuleDto -> {
            writeUserSendingRuleRepository.save(mapper.map(userSendingRuleDto, gutsandgun.kite_user.entity.write.UserSendingRule.class));
        });
    }

}