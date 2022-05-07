package uz.imirsaburov.manage.shop.dto.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponseDTO extends BaseDTO {
    private String path;
    private Long timestamp;
    private String message;
    private Integer status;
    private String errorCode;
}
