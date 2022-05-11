package uz.imirsaburov.manage.shop.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO {

    private Long id;
    private String name;
    private Boolean status;

    public static CategoryDTO toDTO(CategoryEntity entity, CategoryDTO dto) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static CategoryDTO toDTO(CategoryEntity entity) {
        return toDTO(entity, new CategoryDTO());
    }

    public static List<CategoryDTO> toDTO(List<CategoryEntity> entityList) {
        return entityList
                .stream()
                .map(CategoryDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Page<CategoryDTO> toDTO(Page<CategoryEntity> entityPage) {
        return new PageImpl<>(
                toDTO(entityPage.getContent()),
                entityPage.getPageable(),
                entityPage.getTotalElements());
    }
}
