package uz.imirsaburov.manage.shop.exceptions.size;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class SizeNameAlreadyExistException extends AppException {
    public SizeNameAlreadyExistException(String name) {
        super(ExceptionEnum.SIZE_NAME_ALREADY_EXIST_EXCEPTION, name);
    }
}
