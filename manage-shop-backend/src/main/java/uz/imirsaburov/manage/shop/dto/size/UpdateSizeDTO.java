package uz.imirsaburov.manage.shop.dto.size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.SizeEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSizeDTO extends BaseDTO {
    @NotBlank(message = "{validation.create.size.name}")
    private String name;
    @NotNull
    private Boolean status;

    public static SizeEntity toEntity(UpdateSizeDTO dto, SizeEntity entity) {
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
