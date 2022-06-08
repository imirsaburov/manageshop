package uz.imirsaburov.manage.shop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.dto.category.CategoryDTO;
import uz.imirsaburov.manage.shop.dto.product.CreateProductDTO;
import uz.imirsaburov.manage.shop.dto.product.ProductDTO;
import uz.imirsaburov.manage.shop.dto.product.ProductFilter;
import uz.imirsaburov.manage.shop.dto.product.UpdateProductDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;
import uz.imirsaburov.manage.shop.exceptions.product.ProductNotFoundException;
import uz.imirsaburov.manage.shop.repository.ProductRepository;
import uz.imirsaburov.manage.shop.service.CategoryService;
import uz.imirsaburov.manage.shop.service.ProductService;
import uz.imirsaburov.manage.shop.service.ProductSizeService;
import uz.imirsaburov.manage.shop.specification.ProductSpecification;

import java.util.Collections;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final ProductSizeService productSizeService;

    public ProductServiceImpl(ProductRepository repository, CategoryService categoryService,@Lazy ProductSizeService productSizeService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.productSizeService = productSizeService;
    }

    @Override
    public ProductDTO create(CreateProductDTO dto) {

        CategoryDTO byId = categoryService.getById(dto.getCategoryId());

        ProductEntity entity = CreateProductDTO.toEntity(dto);

        repository.saveAndFlush(entity);

        return ProductDTO.toDTO(get(entity.getId()), Collections.emptyList());
    }

    @Override
    public ProductDTO update(Long id, UpdateProductDTO dto) {

        CategoryDTO byId = categoryService.getById(dto.getCategoryId());

        ProductEntity entity = get(id);
        UpdateProductDTO.toEntity(dto, entity);
        repository.save(entity);

        return ProductDTO.toDTO(get(entity.getId()), productSizeService.getListByProduct(id));
    }

    @Override
    public ProductDTO getById(Long id) {
        ProductEntity entity = get(id);
        return ProductDTO.toDTO(get(entity.getId()), productSizeService.getListByProduct(id));
    }

    @Override
    public ProductDTO changeStatus(Long id, ProductEntity.STATUS status) {

        ProductEntity entity = get(id);
        entity.setStatus(status);
        repository.save(entity);

        return ProductDTO.toDTO(get(entity.getId()), productSizeService.getListByProduct(id));
    }

    @Override
    public Page<ProductDTO> getList(ProductFilter filter) {
        BaseSpecification<ProductEntity> all = ProductSpecification.findAll(filter);
        Pageable pageAble = BaseFilterPageable.getPageAble(filter);

        Page<ProductEntity> page = repository.findAll(all, pageAble);

        return page.map(e -> ProductDTO.toDTO(e, productSizeService.getListByProduct(e.getId())));
    }

    @Override
    public ProductDTO delete(Long id) {
        ProductEntity entity = get(id);
        ProductDTO dto = ProductDTO.toDTO(get(entity.getId()), productSizeService.getListByProduct(id));
        repository.delete(entity);

        return dto;
    }

    protected ProductEntity get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
