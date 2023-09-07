package com.cg.views;

import com.cg.service.AuthService;
import com.cg.utils.AppUtils;

import static com.cg.views.ListView.loginList;

public class LoginView {
    public static void loginMenu(){
        try {
            ListView.printMenu(loginList);
            int choice= AppUtils.getIntWithBound("Nhập lựa chọn",0,1);
            if (choice == 0) System.exit(1);
            if (choice == 1) {
                AuthService.login();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " LOGINVIEW");
            loginMenu();
        }

    }
}
