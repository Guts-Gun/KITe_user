package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.service.MessageTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/messageTemplate")
@Log4j2
@RequiredArgsConstructor
public class MessageTemplateController {

    private final MessageTemplateService messageTemplateService;


    /**
     * 사용자 메세지 템플릿 리스트 조회
     *
     * @author solbiko
     * @param principal 로그인 객체
     * @param pageRequestDTO 페이징 객체
     * @return List<MessageTemplateDto> 템플릿 리스스
     */

    @GetMapping("/list")
    public ResponseEntity<Page<MessageTemplateDto>> getMessageTemplateList(Principal principal, PageRequestDTO pageRequestDTO) {

        Page<MessageTemplateDto> messageTemplateDtoList = messageTemplateService.getUserMessageTemplateList(getUserId(principal), pageRequestDTO);
        return new ResponseEntity<>(messageTemplateDtoList, HttpStatus.OK);
    }


    /**
     * 사용자 메세지 템플릿 생성
     *
     * @author solbiko
     * @param principal 로그인 객체
     * @param messageTemplateDto 메시지 템플릿 정보
     * @return List<MessageTemplateDto> 템플릿 리스스
     */
    @PostMapping("/create")
    public ResponseEntity<Long> insertMessageTemplate(Principal principal, @RequestBody MessageTemplateDto messageTemplateDto) {

        Long messageTemplateId = messageTemplateService.insertMessageTemplate(getUserId(principal), messageTemplateDto);
        return new ResponseEntity<>(messageTemplateId, HttpStatus.OK);
    }


    public String getUserId(Principal principal){
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        return token.getTokenAttributes().get("preferred_username").toString();
    }

}
