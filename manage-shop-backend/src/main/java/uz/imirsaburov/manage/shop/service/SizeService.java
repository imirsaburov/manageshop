package uz.imirsaburov.manage.shop.service;

import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.dto.size.SizeDTO;
import uz.imirsaburov.manage.shop.dto.size.SizeFilter;
import uz.imirsaburov.manage.shop.dto.size.CreateSizeDTO;
import uz.imirsaburov.manage.shop.dto.size.UpdateSizeDTO;

public interface SizeService {

    SizeDTO create(CreateSizeDTO dto);

    SizeDTO update(Long id, UpdateSizeDTO dto);

    SizeDTO getById(Long id);

    SizeDTO changeStatus(Long id, Boolean status);

    Page<SizeDTO> getList(SizeFilter filter);

    SizeDTO delete(Long id);
}
