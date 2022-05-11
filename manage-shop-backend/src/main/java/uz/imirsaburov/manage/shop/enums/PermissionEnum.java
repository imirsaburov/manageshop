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
    ;
    private final String description;

    PermissionEnum(String description) {
        this.description = description;
    }
}
