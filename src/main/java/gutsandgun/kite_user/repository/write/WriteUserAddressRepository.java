package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WriteUserAddressRepository extends JpaRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {
    Optional<UserAddress> findByIdAndUserId(Long id, String userId);

    List<UserAddress> findByUserId(String userId);
    List<UserAddress> findByUserId(String userId,Pageable pageable);

    List<UserAddress> findByUserIdAndNameContaining(String userId,String name);
    List<UserAddress> findByUserIdAndNameContaining(String userId,String name,Pageable pageable);

    List<UserAddress> findByUserIdAndName(String userId, String name);

    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone " +
            "from user u left outer join user_address ua on u.id=ua.fk_user_id and ua.is_deleted=0 " +
            "left outer join address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0 " +
            "left outer join address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0 " +
            "where 1=1 " +
            "and u.is_deleted=0 " +
            "and u.id = :userId"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddress(@Param("userId") String userId);


}
