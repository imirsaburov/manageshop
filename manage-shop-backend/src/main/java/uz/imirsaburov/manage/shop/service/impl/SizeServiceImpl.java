package uz.imirsaburov.manage.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.dto.size.SizeDTO;
import uz.imirsaburov.manage.shop.dto.size.SizeFilter;
import uz.imirsaburov.manage.shop.dto.size.CreateSizeDTO;
import uz.imirsaburov.manage.shop.dto.size.UpdateSizeDTO;
import uz.imirsaburov.manage.shop.entity.SizeEntity;
import uz.imirsaburov.manage.shop.exceptions.size.SizeNameAlreadyExistException;
import uz.imirsaburov.manage.shop.exceptions.size.SizeNotFoundException;
import uz.imirsaburov.manage.shop.repository.SizeRepository;
import uz.imirsaburov.manage.shop.service.SizeService;
import uz.imirsaburov.manage.shop.specification.SizeSpecification;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository repository;

    @Override
    public SizeDTO create(CreateSizeDTO dto) {
        if (repository.existsByNameEqualsIgnoreCase(dto.getName()))
            throw new SizeNameAlreadyExistException(dto.getName());

        SizeEntity entity = CreateSizeDTO.toEntity(dto, new SizeEntity());

        repository.save(entity);

        return SizeDTO.toDTO(entity);
    }

    @Override
    public SizeDTO update(Long id, UpdateSizeDTO dto) {
        if (repository.existsByNameEqualsIgnoreCaseAndIdNot(dto.getName(), id))
            throw new SizeNameAlreadyExistException(dto.getName());

        SizeEntity entity = get(id);
        UpdateSizeDTO.toEntity(dto, entity);
        repository.save(entity);

        return SizeDTO.toDTO(entity);
    }

    @Override
    public SizeDTO getById(Long id) {
        SizeEntity entity = get(id);
        return SizeDTO.toDTO(entity);
    }

    @Override
    public SizeDTO changeStatus(Long id, Boolean status) {

        SizeEntity entity = get(id);
        entity.setStatus(status);
        repository.save(entity);

        return SizeDTO.toDTO(entity);
    }

    @Override
    public Page<SizeDTO> getList(SizeFilter filter) {
        BaseSpecification<SizeEntity> all = SizeSpecification.findAll(filter);
        Pageable pageAble = BaseFilterPageable.getPageAble(filter);

        Page<SizeEntity> page = repository.findAll(all, pageAble);

        return SizeDTO.toDTO(page);
    }

    @Override
    public SizeDTO delete(Long id) {
        SizeEntity entity = get(id);
        SizeDTO dto = SizeDTO.toDTO(entity);
        repository.delete(entity);

        return dto;
    }

    protected SizeEntity get(Long id) {
        return repository.findById(id).orElseThrow(() -> new SizeNotFoundException(id));
    }
}
