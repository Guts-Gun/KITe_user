package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserSendingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteUserSendingRuleRepository extends JpaRepository<UserSendingRule, Long> {
}
