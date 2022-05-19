package uz.imirsaburov.manage.shop.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.CategoryEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCategoryDTO extends BaseDTO {
    @NotBlank(message = "{validation.create.category.name}")
    private String name;
    @NotNull
    private Boolean status;

    public static CategoryEntity toEntity(UpdateCategoryDTO dto, CategoryEntity entity) {
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
