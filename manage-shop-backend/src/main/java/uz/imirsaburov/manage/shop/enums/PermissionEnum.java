package uz.imirsaburov.manage.shop.enums;

import lombok.Getter;

@Getter
public enum PermissionEnum {
    TEST("TEST uchun");

    private final String description;

    PermissionEnum(String description) {
        this.description = description;
    }
}
