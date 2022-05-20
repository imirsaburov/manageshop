package uz.imirsaburov.manage.shop.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import uz.imirsaburov.manage.shop.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "sizes")
public class SizeEntity extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ColumnDefault("false")
    @Column(name = "status", nullable = false)
    private Boolean status;
}
