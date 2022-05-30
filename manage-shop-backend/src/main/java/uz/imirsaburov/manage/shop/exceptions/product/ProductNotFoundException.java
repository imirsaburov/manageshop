package uz.imirsaburov.manage.shop.exceptions.product;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class ProductNotFoundException extends AppException {
    public ProductNotFoundException(Long id) {
        super(ExceptionEnum.PRODUCT_NOT_FOUND_EXCEPTION, String.valueOf(id));
    }
}
