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
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Column(name = "incoming_price")
    private Long incomingPrice;

    @Column(name = "min_sell_price")
    private Long minSellPrice;

    @Column(name = "sell_price")
    private Long sellPrice;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity categoryEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private STATUS status;

    public static enum STATUS {
        ACTIVE,
        INACTIVE

    }
}
