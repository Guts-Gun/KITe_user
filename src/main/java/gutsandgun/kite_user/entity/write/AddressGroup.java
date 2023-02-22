package gutsandgun.kite_user.entity.write;

import gutsandgun.kite_user.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * group-address relation table
 */
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql= "UPDATE address_group SET is_deleted=true WHERE id = ?")
@Table(
		name="address_group",
		indexes = {
		@Index(name = "idx_address_group_user_group_id", columnList = "fk_user_group_id")
		})
@NoArgsConstructor
public class AddressGroup extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "fk_user_address_id")
	@Comment("주소록 ID")
	private Long userAddressId;

	@Column(name = "fk_user_group_id")
	@Comment("그룹 ID")
	private Long userGroupId;

	@ColumnDefault("false")
	private Boolean isDeleted = false;

	@Comment("생성자")
	@Column(name = "reg_id", nullable = false, length = 20)
	private String regId;

    @Comment("수정자")
    @Column(name = "mod_id", length = 20)
    private String modId;


    @Builder
    public AddressGroup(String regId,Long userAddressId,Long userGroupId){
        this.userAddressId = userAddressId;
        this.userGroupId = userGroupId;
        this.regId = regId;
    }


}
