package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.AddressEmail;
import gutsandgun.kite_user.entity.write.AddressGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface WriteAddressGroupRepository extends JpaRepository<AddressGroup, Long>, JpaSpecificationExecutor<AddressGroup> {
    List<AddressGroup> findByUserGroupId(Long userGroupId);
    Long countByUserGroupId(Long userGroupId);
    List<AddressGroup> findByUserAddressId(Long userAddressId);
    Optional<AddressGroup> findByUserAddressIdAndUserGroupId(Long userAddressId, Long userGroupId);

}
