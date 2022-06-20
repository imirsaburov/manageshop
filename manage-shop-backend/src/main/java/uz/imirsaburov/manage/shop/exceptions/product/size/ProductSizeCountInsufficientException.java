package uz.imirsaburov.manage.shop.exceptions.product.size;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class ProductSizeCountInsufficientException extends AppException {
    public ProductSizeCountInsufficientException() {
        super(ExceptionEnum.PRODUCT_SIZE_INSUFFICIENT_EXCEPTION);
    }
}
