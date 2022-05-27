package uz.imirsaburov.manage.shop.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import uz.imirsaburov.manage.shop.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "files")
@Where(clause = " deleted = 'false' ")
public class FileEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "size", nullable = false)
    private Long size;
}
