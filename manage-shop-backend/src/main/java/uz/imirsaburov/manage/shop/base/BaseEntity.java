package uz.imirsaburov.manage.shop.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity extends SerializableImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "modified_at")
    private Date modifiedAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "deleted")
    private Boolean deleted = false;
}
