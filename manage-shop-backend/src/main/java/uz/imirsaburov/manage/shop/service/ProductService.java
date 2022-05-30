package uz.imirsaburov.manage.shop.service;

import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.dto.product.*;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

public interface ProductService {

    ProductDTO create(CreateProductDTO dto);

    ProductDTO update(Long id, UpdateProductDTO dto);

    ProductDTO getById(Long id);

    ProductDTO changeStatus(Long id, ProductEntity.STATUS status);

    Page<ProductDTO> getList(ProductFilter filter);

    ProductDTO delete(Long id);
}
