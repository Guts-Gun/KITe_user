package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.MessageTemplate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

    @Transactional
    void deleteByIdAndUserId(Long messageTemplateId, String userId);

}
