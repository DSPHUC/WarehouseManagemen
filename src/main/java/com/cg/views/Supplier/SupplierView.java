package com.cg.views.Supplier;

import com.cg.model.Supplier;
import com.cg.service.SupplierService;
import com.cg.utils.AppUtils;
import com.cg.views.ListView;

import static com.cg.model.Supplier.getNextId;
import static com.cg.service.SupplierService.supplierList;
import static com.cg.views.Manager.ManagerView.managerMenu;

public class SupplierView {
    static SupplierService supplier1 = new SupplierService();

    public static void importSupplier() {
        ListView.printMenu(ListView.managerVendorListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 4);
        switch (choice) {
            case 1 -> showList();
            case 2 -> addSupplier();
            case 3 -> editSupplier();
            case 4 -> removeSupplier();
            case 0 -> managerMenu();
        }
    }

    public static void removeSupplier() {
        showList();
        long id = AppUtils.getInt("Nhập id cần xóa");
        supplier1.remove(id);
        System.out.println("Xóa thành công");
    }

    public static void editSupplier() {
        try {
            System.out.println("Thay đổi thông tin nhà cung cấp");
            long id = AppUtils.getLongId("Nhập id cần thay đổi");
            if (supplier1.isExist(id)) {
                Supplier supplier = supplier1.findById(id);
                ListView.printMenu(ListView.editSuplierList);
                int choice = AppUtils.getIntWithBound("Nhập lựa chọn",0,4);
                switch (choice) {
                    case 1 -> {
                        String name = AppUtils.getString("Nhập tên mới: ");
                        supplier.setName(name);
                    }
                    case 2 -> {
                        String address = AppUtils.getString("Nhập địa chỉ mới: ");
                        supplier.setAddress(address);
                    }
                    case 3 -> {
                        String phone = AppUtils.getString("Nhập số điện thoại mới: ");
                        supplier.setPhone(phone);
                    }
                    case 4 -> {
                        String email = AppUtils.getString("Nhập email mới: ");
                        supplier.setEmail(email);
                    }
                    case 0 ->  importSupplier();
                }
                supplier1.edit(id, supplier);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        importSupplier();
    }
    public static void addSupplier() {
        String name = AppUtils.getString("Nhập tên nhà cung cấp");
        String address = AppUtils.getString("Nhập địa chỉ");
        String phone = AppUtils.getString("Nhập số điện thoại");
        String email = AppUtils.getString("Nhập email");
        Supplier supplier = new Supplier(getNextId(), name, address, phone, email);
        supplier1.creat(supplier);
        importSupplier();
    }

    public static void showList() {
        System.out.println("Danh sách nhà cung cấp");
        int[] maxWidths = new int[5];
        for (Supplier supplier : supplierList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(supplier.getId()).length());
            maxWidths[1] = Math.max(maxWidths[1], supplier.getName().length());
            maxWidths[2] = Math.max(maxWidths[2], supplier.getAddress().length());
            maxWidths[3] = Math.max(maxWidths[3], supplier.getPhone().length());
            maxWidths[4] = Math.max(maxWidths[4], supplier.getEmail().length());
        }
        String format = "%-" + (maxWidths[0] + 1) + "s | %-" + (maxWidths[1] + 1) + "s | %-"
                + (maxWidths[2] + 1) + "s | %-" + (maxWidths[3] + 1) + "s | %-"
                + (maxWidths[4] + 1) + "s\n";
        System.out.printf(
                format
                , "id", "Nhà cung cấp", "Địa chỉ", "Số điện thoại", "Email");
        for (Supplier supplier : supplierList) {
            System.out.printf(format
                    , supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getPhone()
                    , supplier.getEmail());
        }
        importSupplier();
    }
}
