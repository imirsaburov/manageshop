package uz.imirsaburov.manage.shop.entity;

import lombok.Getter;
import lombok.Setter;
import uz.imirsaburov.manage.shop.base.BaseEntity;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class PermissionEntity extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionEnum name;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
