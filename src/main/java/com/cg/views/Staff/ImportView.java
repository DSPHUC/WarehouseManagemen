package com.cg.views.Staff;

import com.cg.utils.AppUtils;
import com.cg.views.Bill.ReceiptView;
import com.cg.views.InventoryView;
import com.cg.views.ListView;

import java.util.Scanner;

import static com.cg.views.LoginView.loginMenu;

public class ImportView {
    static Scanner scanner = new Scanner(System.in);

    public static void importMenu() {
        System.out.println("Waiting for Upgrade......");
        ListView.printMenu(ListView.importStaffListMenu);
//        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 2);
//        ReceiptView.importReceipt();
//        switch (choice) {
//            case 1 -> {
//                ListView.printMenu(ListView.InOutMenu);
//                int choose = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 2);
//                switch (choose) {
//                    case 1 -> {
//                        ReceiptView.receiptInput();
//                    }
//                    case 2 -> {
//                    }
//                    case 0 -> importMenu();
//
//                }
//            }
////            "1. Nhập hàng",
////                    "2. Lập phiếu nhập hàng",
////                    "0. Back");
//            case 2 -> {
//                ReceiptView.showReceiptInputList();
//            }
//            case 3 -> {
//                // xóa phiếu
//            }
//            case 0 -> loginMenu();
//        }
    }
}
