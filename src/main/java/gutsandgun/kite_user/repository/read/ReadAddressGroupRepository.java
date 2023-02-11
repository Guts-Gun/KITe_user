package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.AddressGroup;
import gutsandgun.kite_user.entity.write.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadAddressGroupRepository extends JpaRepository<AddressGroup, Long> {
    List<AddressGroup> findByUserAddressId(Long userAddressId);
    Long countByUserGroupId(Long userGroupId);

    List<AddressGroup> findByUserGroupId(Long userGroupId);

}
