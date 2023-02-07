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
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
            "from user_address ua\n" +
            "left outer join address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0\n" +
            "left outer join address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0\n" +
            "where ua.is_deleted=0 and  ua.fk_user_id=:userId"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddressByUserId(@Param("userId") String userId);


    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
            "from user.user_address as ua\n" +
            "left join user.address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0\n" +
            "left join user.address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0\n" +
            "where ua.is_deleted=0 and ua.fk_user_id=:userId and ua.name like %:name%\n" +
            "and (ae.email is not null or ap.phone is not null)"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddressByUserIdAndNameContaining(@Param("userId") String userId,@Param("name") String name);



    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
            "from user.user_address ua\n" +
            "inner join user.address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0 and ae.email like %:searchEmail%\n" +
            "left join user.address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0\n" +
            "where ua.is_deleted=0 and ua.fk_user_id=:userId\n" +
            "and (ae.email is not null or ap.phone is not null)"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddressByUserIdAndEmailContaining(@Param("userId") String userId,@Param("searchEmail")String searchEmail);


    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
            "from user.user_address ua\n" +
            "inner join user.address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0 and ap.phone like %:searchPhone%\n" +
            "left join user.address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0\n" +
            "where ua.is_deleted=0 and ua.fk_user_id=:userId\n" +
            "and (ae.email is not null or ap.phone is not null)"
            , nativeQuery = true)

    List<Map<String, String>>  findUserAddressByUserIdAndPhoneContaining(@Param("userId") String userId,@Param("searchPhone")String searchPhone);


    //pageable
    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
                    "from user_address ua\n" +
                    "left outer join address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0\n" +
                    "left outer join address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0\n" +
                    "where ua.is_deleted=0 and  ua.fk_user_id=:userId"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddressByUserId(@Param("userId") String userId,Pageable pageable);


    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
                    "from user.user_address as ua\n" +
                    "left join user.address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0\n" +
                    "left join user.address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0\n" +
                    "where ua.is_deleted=0 and ua.fk_user_id=:userId and ua.name like %:name%\n" +
                    "and (ae.email is not null or ap.phone is not null)"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddressByUserIdAndNameContaining(@Param("userId") String userId,@Param("name") String name,Pageable pageable);



    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
                    "from user.user_address ua\n" +
                    "inner join user.address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0 and ae.email like %:searchEmail%\n" +
                    "left join user.address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0\n" +
                    "where ua.is_deleted=0 and ua.fk_user_id=:userId\n" +
                    "and (ae.email is not null or ap.phone is not null)"
            , nativeQuery = true)
    List<Map<String, String>>  findUserAddressByUserIdAndEmailContaining(@Param("userId") String userId,@Param("searchEmail")String searchEmail,Pageable pageable);


    @Query(value =
            "select ua.id userAddressId, ua.name, ae.email, ap.phone\n" +
                    "from user.user_address ua\n" +
                    "inner join user.address_phone ap on ua.id=ap.fk_user_address_id and ap.is_deleted=0 and ap.phone like %:searchPhone%\n" +
                    "left join user.address_email ae on ua.id=ae.fk_user_address_id and ae.is_deleted=0\n" +
                    "where ua.is_deleted=0 and ua.fk_user_id=:userId\n" +
                    "and (ae.email is not null or ap.phone is not null)"
            , nativeQuery = true)

    List<Map<String, String>>  findUserAddressByUserIdAndPhoneContaining(@Param("userId") String userId,@Param("searchPhone")String searchPhone,Pageable pageable);
}
