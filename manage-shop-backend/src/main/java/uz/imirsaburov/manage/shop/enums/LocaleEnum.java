package uz.imirsaburov.manage.shop.enums;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum LocaleEnum {
    UZ(Locale.FRANCE),
    RU(Locale.ITALIAN),
    ENG(Locale.ENGLISH);

    private Locale locale;

    LocaleEnum(Locale locale) {
        this.locale = locale;
    }
}
