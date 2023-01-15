package gutsandgun.kite_user.entity.write;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE address_phone SET is_deleted=true WHERE id = ?")
@Table(name="address_phone")
@NoArgsConstructor
public class AddressPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_address_id")
    private Long userAddressId;

    private String phone;

    private Boolean isDeleted = false;

    @Builder
    public AddressPhone(Long userAddressId, String phone) {
        this.userAddressId = userAddressId;
        this.phone = phone;
    }
}
