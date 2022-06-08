package uz.imirsaburov.manage.shop.service;

import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.dto.product.productSize.CreateProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeFilter;
import uz.imirsaburov.manage.shop.dto.product.productSize.UpdateProductSizeDTO;

import java.util.List;

public interface ProductSizeService {

    ProductSizeDTO create(CreateProductSizeDTO dto);

    ProductSizeDTO update(Long id, UpdateProductSizeDTO dto);

    ProductSizeDTO getById(Long id);

//    ProductSizeDTO changeStatus(Long id, Boolean status);

    Page<ProductSizeDTO> getList(ProductSizeFilter filter);

    ProductSizeDTO delete(Long id);

    List<ProductSizeDTO> getListByProduct(Long productId);

    boolean exist(Long id);
}
