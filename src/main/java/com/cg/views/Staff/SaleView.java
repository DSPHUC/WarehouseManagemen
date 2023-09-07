package com.cg.views.Staff;

import com.cg.utils.AppUtils;
import com.cg.views.Bill.ReceiptView;
import com.cg.views.InventoryView;
import com.cg.views.ListView;
import com.cg.views.Product.ProductView;

import static com.cg.views.LoginView.loginMenu;

public class SaleView {
    public static void SaleMenu() {
        ListView.printMenu(ListView.salesStaffListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 3);
        switch (choice) {
            case 1 -> ReceiptView.addDetail();
            case 2 -> {
                ProductView.showProductList();
                SaleMenu();
            }
            case 3 -> InventoryView.showInventory();
            case 0 -> loginMenu();
        }
    }
}
