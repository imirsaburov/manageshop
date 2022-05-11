package uz.imirsaburov.manage.shop.service;

import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.enums.RoleEnum;

public interface AuthoritySerivce {

    /**
     * Check if permission is existing, it is true
     *
     * @param username
     * @param permission
     * @return
     */
    boolean checkPermission(String username, PermissionEnum permission);

    boolean checkAdmin(String username);

    boolean checkRole(String username, RoleEnum role);
}
