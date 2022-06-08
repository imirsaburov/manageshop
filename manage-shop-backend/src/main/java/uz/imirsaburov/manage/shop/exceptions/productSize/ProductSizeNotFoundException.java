package uz.imirsaburov.manage.shop.exceptions.productSize;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class ProductSizeNotFoundException extends AppException {
    public ProductSizeNotFoundException(Long id) {
        super(ExceptionEnum.PRODUCT_NOT_FOUND_EXCEPTION, String.valueOf(id));
    }
}
