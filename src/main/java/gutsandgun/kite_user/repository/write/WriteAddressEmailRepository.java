package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.AddressEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WriteAddressEmailRepository extends JpaRepository<AddressEmail, Long>, JpaSpecificationExecutor<AddressEmail> {
}
