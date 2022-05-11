package uz.imirsaburov.manage.shop.exceptions.category;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class CategoryNameAlreadyExistException extends AppException {
    public CategoryNameAlreadyExistException(String name) {
        super(ExceptionEnum.CATEGORY_NAME_ALREADY_EXIST_EXCEPTION, name);
    }
}
