package uz.imirsaburov.manage.shop.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;
import uz.imirsaburov.manage.shop.base.BaseEntity;
import uz.imirsaburov.manage.shop.entity.PermissionEntity;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "user_permissions")
@Where(clause = " deleted = 'false' ")
public class UserPermissionEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private PermissionEntity permissionEntity;
}
