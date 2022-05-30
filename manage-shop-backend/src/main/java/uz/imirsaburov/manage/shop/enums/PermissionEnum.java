package uz.imirsaburov.manage.shop.enums;

import lombok.Getter;

@Getter
public enum PermissionEnum {
    TEST("TEST uchun"),
    CATEGORY_LIST("get all category"),
    CATEGORY_CREATE("it can create category"),
    CATEGORY_UPDATE("it can update category"),
    CATEGORY_CHANGE_STATUS("it can change status of category"),
    CATEGORY_DELETE("it can delete category"),

    SIZE_LIST("get all size"),
    SIZE_CREATE("it can create size"),
    SIZE_UPDATE("it can update size"),
    SIZE_CHANGE_STATUS("it can change status of size"),
    SIZE_DELETE("it can delete size"),

    PRODUCT_LIST("get all product"),
    PRODUCT_CREATE("it can create product"),
    PRODUCT_UPDATE("it can update product"),
    PRODUCT_CHANGE_STATUS("it can change status of product"),
    PRODUCT_DELETE("it can delete product"),
    ;
    private final String description;

    PermissionEnum(String description) {
        this.description = description;
    }
}
