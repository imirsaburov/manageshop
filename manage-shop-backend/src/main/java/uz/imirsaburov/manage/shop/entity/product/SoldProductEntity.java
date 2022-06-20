package uz.imirsaburov.manage.shop.entity.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.imirsaburov.manage.shop.base.BaseEntity;
import uz.imirsaburov.manage.shop.entity.CategoryEntity;
import uz.imirsaburov.manage.shop.entity.SizeEntity;

import javax.persistence.*;


@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "sold_products")
public class SoldProductEntity extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity productEntity;

    @Column(name = "size_id", nullable = false)
    private Long sizeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "size_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private SizeEntity sizeEntity;


    @Column(name = "price")
    private Long price;

}
