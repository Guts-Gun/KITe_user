package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.AddressPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WriteAddressPhoneRepository extends JpaRepository<AddressPhone, Long>, JpaSpecificationExecutor<AddressPhone> {
}
