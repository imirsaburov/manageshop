package uz.imirsaburov.manage.shop.exceptions;

import uz.imirsaburov.manage.shop.base.BaseEntity;
import uz.imirsaburov.manage.shop.enums.ExceptionEnum;

public class SpecificationCustomMethodNotReadyException extends AppException {
    public <T extends BaseEntity> SpecificationCustomMethodNotReadyException(Class<T> entityClass) {
        super(ExceptionEnum.USERNAME_NOT_FOUND_EXCEPTION, entityClass.getSimpleName());
    }
}
