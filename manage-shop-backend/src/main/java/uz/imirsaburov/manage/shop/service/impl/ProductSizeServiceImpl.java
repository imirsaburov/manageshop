package uz.imirsaburov.manage.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.dto.product.ProductDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.CreateProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeFilter;
import uz.imirsaburov.manage.shop.dto.product.productSize.UpdateProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.size.SizeDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;
import uz.imirsaburov.manage.shop.exceptions.product.size.ProductSizeCountInsufficientException;
import uz.imirsaburov.manage.shop.exceptions.product.size.ProductSizeExistException;
import uz.imirsaburov.manage.shop.exceptions.product.size.ProductSizeNotFoundException;
import uz.imirsaburov.manage.shop.repository.ProductSizeRepository;
import uz.imirsaburov.manage.shop.service.ProductService;
import uz.imirsaburov.manage.shop.service.ProductSizeService;
import uz.imirsaburov.manage.shop.service.SizeService;
import uz.imirsaburov.manage.shop.specification.ProductSizeSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository repository;
    private final ProductService productService;
    private final SizeService sizeService;

    @Override
    public ProductSizeDTO create(CreateProductSizeDTO dto) {

        ProductDTO productDTO = productService.getById(dto.getProductId());

        SizeDTO sizeDTO = sizeService.getById(dto.getSizeId());

        if (repository.existsBySizeIdAndProductId(dto.getSizeId(), dto.getProductId()))
            throw new ProductSizeExistException();

        ProductSizeEntity entity = CreateProductSizeDTO.toEntity(dto);

        repository.save(entity);

        return null;
    }

    @Override
    public ProductSizeDTO update(Long id, UpdateProductSizeDTO dto) {

        ProductSizeEntity entity = get(id);

        ProductDTO productDTO = productService.getById(dto.getProductId());

        SizeDTO sizeDTO = sizeService.getById(dto.getSizeId());

        if (repository.existsBySizeIdAndProductIdAndIdNot(dto.getSizeId(), dto.getProductId(), id))
            throw new ProductSizeExistException();

        UpdateProductSizeDTO.toEntity(dto, entity);
        repository.save(entity);

        return ProductSizeDTO.toDTO(entity);
    }

    @Override
    public ProductSizeDTO getById(Long id) {
        ProductSizeEntity entity = get(id);
        return ProductSizeDTO.toDTO(entity);
    }

//    @Override
//    public ProductSizeDTO changeStatus(Long id, Boolean status) {
//
//        ProductSizeEntity entity = get(id);
//        entity.setStatus(status);
//        repository.save(entity);
//
//        return ProductSizeDTO.toDTO(entity);
//    }

    @Override
    public Page<ProductSizeDTO> getList(ProductSizeFilter filter) {
        BaseSpecification<ProductSizeEntity> all = ProductSizeSpecification.findAll(filter);
        Pageable pageAble = BaseFilterPageable.getPageAble(filter);

        Page<ProductSizeEntity> page = repository.findAll(all, pageAble);

        return ProductSizeDTO.toDTO(page);
    }

    @Override
    public ProductSizeDTO delete(Long id) {
        ProductSizeEntity entity = get(id);
        ProductSizeDTO dto = ProductSizeDTO.toDTO(entity);
        repository.delete(entity);

        return dto;
    }

    @Override
    public List<ProductSizeDTO> getListByProduct(Long productId) {
        ProductSizeFilter productSizeFilter = new ProductSizeFilter();
        productSizeFilter.setProductId(productId);
        productSizeFilter.setSize(100000);
        productSizeFilter.setPage(0);
        return getList(productSizeFilter).getContent();
    }

    @Override
    public boolean exist(Long id) {
        return repository.existsById(id);
    }

    @Override
    public ProductSizeDTO incrementSoldCount(Long id, int soldCount) {
        ProductSizeEntity entity = get(id);

        if (entity.getCount()<soldCount)
            throw new ProductSizeCountInsufficientException();

        entity.setCount(entity.getCount()-soldCount);
        entity.setSoldCount(entity.getSoldCount()+soldCount);

        repository.save(entity);

        return ProductSizeDTO.toDTO(entity);
    }

    protected ProductSizeEntity get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductSizeNotFoundException(id));
    }
}
