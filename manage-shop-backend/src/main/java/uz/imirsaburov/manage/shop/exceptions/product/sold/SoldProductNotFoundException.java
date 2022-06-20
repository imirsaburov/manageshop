package uz.imirsaburov.manage.shop.exceptions.product.sold;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class SoldProductNotFoundException extends AppException {
    public SoldProductNotFoundException(Long id) {
        super(ExceptionEnum.SOLD_PRODUCT_NOT_FOUND_EXCEPTION, String.valueOf(id));
    }
}
