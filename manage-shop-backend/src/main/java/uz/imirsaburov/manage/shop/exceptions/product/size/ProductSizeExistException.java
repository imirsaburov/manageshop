package uz.imirsaburov.manage.shop.exceptions.product.size;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class ProductSizeExistException extends AppException {
    public ProductSizeExistException() {
        super(ExceptionEnum.PRODUCT_NOT_FOUND_EXCEPTION);
    }
}
