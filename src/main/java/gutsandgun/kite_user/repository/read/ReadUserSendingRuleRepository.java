package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.UserSendingRule;
import gutsandgun.kite_user.type.SendingRuleType;
import gutsandgun.kite_user.type.SendingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReadUserSendingRuleRepository extends JpaRepository<UserSendingRule, Long> {

    @Query(value = "SELECT b.*, usr.id user_sending_rule_id, usr.weight " +
            "FROM broker b LEFT OUTER JOIN user_sending_rule usr ON b.id = usr.fk_broker_id AND usr.is_deleted=0 AND usr.fk_user_id = :userId " +
            "WHERE 1=1 " +
            "AND sending_type=:sendingType " +
            "AND b.is_deleted=0", nativeQuery = true)
    List<Map<String,Object>> findByUserId(@Param("userId") String userId, @Param("sendingType") int sendingType);
}
