package uz.imirsaburov.manage.shop.service;

import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.dto.product.sold.CreateSoldProductDTO;
import uz.imirsaburov.manage.shop.dto.product.sold.SoldProductDTO;
import uz.imirsaburov.manage.shop.dto.product.sold.SoldProductFilter;

public interface SoldProductService {

    SoldProductDTO create(CreateSoldProductDTO dto);

    SoldProductDTO getById (Long id);

    Page<SoldProductDTO> getList(SoldProductFilter filter);
}
