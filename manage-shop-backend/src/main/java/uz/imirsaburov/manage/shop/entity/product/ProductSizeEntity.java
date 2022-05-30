package uz.imirsaburov.manage.shop.entity.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.imirsaburov.manage.shop.base.BaseEntity;
import uz.imirsaburov.manage.shop.entity.SizeEntity;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "product_sizes")
public class ProductSizeEntity extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity productEntity;

    @Column(name = "size_id", nullable = false)
    private Long sizeId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private SizeEntity sizeEntity;

    @Column(name = "count")
    private Long count;

    @Column(name = "sold_count")
    private Long soldCount;
}
