package uz.imirsaburov.manage.shop.exceptions.size;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class SizeNotFoundException extends AppException {
    public SizeNotFoundException(Long id) {
        super(ExceptionEnum.SIZE_NOT_FOUND_EXCEPTION, String.valueOf(id));
    }
}
