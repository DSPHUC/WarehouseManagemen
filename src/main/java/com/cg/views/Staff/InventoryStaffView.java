package com.cg.views.Staff;

import com.cg.utils.AppUtils;
import com.cg.views.Bill.ReceiptView;
import com.cg.views.InventoryView;
import com.cg.views.ListView;
import static com.cg.views.LoginView.loginMenu;

public class InventoryStaffView {
    public static void inventoryStaffMenu() {
        ListView.printMenu(ListView.inventoryStaffListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 2);
        switch (choice) {
            case 1 -> ReceiptView.showDetailIn_Output();
            case 2 -> InventoryView.showInventory();
            case 0-> loginMenu();
        }
    }
}
