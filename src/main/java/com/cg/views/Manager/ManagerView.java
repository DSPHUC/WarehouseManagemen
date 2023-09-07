package com.cg.views.Manager;

import com.cg.utils.AppUtils;
import com.cg.views.Bill.ReceiptView;
import com.cg.views.ListView;
import com.cg.views.Product.ProductCategoryView;
import com.cg.views.Product.ProductGroupView;
import com.cg.views.Product.ProductView;
import com.cg.views.Staff.StaffView;
import com.cg.views.Supplier.SupplierView;

import java.util.Scanner;

import static com.cg.service.AuthService.login;
import static com.cg.views.ListView.managerStaffListMenu;
import static com.cg.views.LoginView.loginMenu;

public class ManagerView {

    public static void managerMenu() {
        ListView.printMenu(ListView.managerListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 6);
        switch (choice) {
            case 1 -> ReceiptView.importReceipt();
            case 2 -> StaffView.importStaff();
            case 3 -> ProductCategoryView.importProductCategory();
            case 4 -> ProductGroupView.importProductGroup();
            case 5 -> ProductView.importProduct();
            case 6 -> SupplierView.importSupplier();
            case 0 -> loginMenu();
        }
    }
}
