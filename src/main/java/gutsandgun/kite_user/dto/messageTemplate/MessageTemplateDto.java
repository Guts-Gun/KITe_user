package gutsandgun.kite_user.dto.messageTemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageTemplateDto {

    private Long id;
    private String userId;
    private String title;
    private String content;
    private String regId;
    private LocalDateTime regDt;
    private String ModId;
    private LocalDateTime modDt;
}
