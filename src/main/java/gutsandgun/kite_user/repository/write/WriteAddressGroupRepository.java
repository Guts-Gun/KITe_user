package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.AddressGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WriteAddressGroupRepository extends JpaRepository<AddressGroup, Long>, JpaSpecificationExecutor<AddressGroup> {
}
