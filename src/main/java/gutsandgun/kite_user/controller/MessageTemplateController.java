package gutsandgun.kite_user.controller;

import gutsandgun.kite_user.dto.PageRequestDTO;
import gutsandgun.kite_user.dto.messageTemplate.MessageTemplateDto;
import gutsandgun.kite_user.exception.CustomException;
import gutsandgun.kite_user.exception.ErrorCode;
import gutsandgun.kite_user.service.MessageTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/create")
    public ResponseEntity<Long> insertMessageTemplate(Principal principal, @RequestBody MessageTemplateDto messageTemplateDto) {

        Long messageTemplateId = messageTemplateService.insertMessageTemplate(getUserId(principal), messageTemplateDto);
        return new ResponseEntity<>(messageTemplateId, HttpStatus.OK);
    }

    /**
     * 사용자 메세지 템플릿 삭제
     *
     * @author solbiko
     * @param principal 로그인 객체
     * @param messageTemplateList 삭제할 메시지 템플릿 아이디 리스트
     * @return List<MessageTemplateDto> 템플릿 리스스
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteMessageTemplate(Principal principal, @RequestBody List<Long> messageTemplateList) {
        messageTemplateService.deleteMessageTemplate(getUserId(principal), messageTemplateList);
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
