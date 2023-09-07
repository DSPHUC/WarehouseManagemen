package com.cg.service;


import com.cg.Enum.ERole;
import com.cg.service.UserService.*;
import com.cg.utils.AppUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.cg.views.Manager.ManagerView.managerMenu;
import static com.cg.views.Staff.AuditView.auditMenu;
import static com.cg.views.Staff.ImportView.importMenu;
import static com.cg.views.Staff.InventoryStaffView.inventoryStaffMenu;
import static com.cg.views.Staff.SaleView.SaleMenu;

public class AuthService {
    static StaffService staff = new StaffService();
    public static List<String> name_role = new ArrayList<>();

    public static void login() {
        String user = AppUtils.getString("Tên đăng nhập: ");
        String pass = AppUtils.getString("Mật khẩu: ");
        String role;
        String name;
        if (ManagerService.getByUserName(user) != null && Objects.equals(ManagerService.getByUserName(user).getPassword(), pass)) {
            name = ManagerService.getByUserName(user).getName();
            System.out.println("Welcome " + ManagerService.getByName(user));
            role = "Manager";
            name_role = Arrays.asList(name, role);
            managerMenu();
        } else if (StaffService.getByUserName(user) != null && Objects.equals(StaffService.getByUserName(user).getPassword(), pass)) {
            name = StaffService.getByUserName(user).toString();
            if (staff.checkRole(user).get().equals(ERole.SALES)) {
                SaleMenu();
            } else if (staff.checkRole(user).get().equals(ERole.AUDIT)) {
                auditMenu();
            } else if (staff.checkRole(user).get().equals(ERole.IMPORT)) {
                importMenu();
            } else if (staff.checkRole(user).get().equals(ERole.INVENTORY)) {
                inventoryStaffMenu();
            } else {
                System.out.println("Invalid account!");
                login();
            }
            role = staff.checkRole(user).toString();
            name_role = Arrays.asList(name, role);
        }
    }
}
