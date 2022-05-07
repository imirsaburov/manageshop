package uz.imirsaburov.manage.shop.exceptions.category;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class CategoryNotFoundException extends AppException {
    public CategoryNotFoundException(Long id) {
        super(ExceptionEnum.CATEGORY_NOT_FOUND_EXCEPTION, String.valueOf(id));
    }
}
