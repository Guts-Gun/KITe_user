package gutsandgun.kite_user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserSendingRuleDto {
    private Long id;
    private String userId;
    private Long brokerId;
    private Long weight;
    private String regId;
    private String regDt;

}