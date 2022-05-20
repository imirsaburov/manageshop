package uz.imirsaburov.manage.shop.dto.size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.SizeEntity;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class SizeDTO extends BaseDTO {

    private Long id;
    private String name;
    private Boolean status;

    public static SizeDTO toDTO(SizeEntity entity, SizeDTO dto) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static SizeDTO toDTO(SizeEntity entity) {
        return toDTO(entity, new SizeDTO());
    }

    public static List<SizeDTO> toDTO(List<SizeEntity> entityList) {
        return entityList
                .stream()
                .map(SizeDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Page<SizeDTO> toDTO(Page<SizeEntity> entityPage) {
        return new PageImpl<>(
                toDTO(entityPage.getContent()),
                entityPage.getPageable(),
                entityPage.getTotalElements());
    }
}
