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
@SQLDelete(sql= "UPDATE address_email SET is_deleted=true WHERE id = ?")
@Table(name="address_email")
@NoArgsConstructor
public class AddressEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_address_id")
    private Long userAddressId;

    private String email;

    private Boolean isDeleted = false;


    @Builder
    public AddressEmail(Long userAddressId, String email){
        this.userAddressId = userAddressId;
        this.email = email;
    }
}
