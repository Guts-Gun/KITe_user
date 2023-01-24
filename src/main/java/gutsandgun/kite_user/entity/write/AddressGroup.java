package gutsandgun.kite_user.entity.write;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import gutsandgun.kite_sendmanager.entity.BaseTimeEntity;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE address_group SET is_deleted=true WHERE id = ?")
@Table(name="address_group")
@NoArgsConstructor
public class AddressGroup extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_user_address_id")
    private Long userAddressId;

    @Column(name = "fk_user_group_id")
    private Long userGroupId;

    private Boolean isDeleted = false;

    @Comment("생성자")
    @Column(name = "reg_id", nullable = false, length = 20)
    private Long regId;

    @Comment("수정자")
    @Column(name = "mod_id", length = 20)
    private Long ModId;


    @Builder
    public AddressGroup(Long regId,Long userAddressId,Long userGroupId){
        this.userAddressId = userAddressId;
        this.userGroupId = userGroupId;
        this.regId = regId;
    }


}
