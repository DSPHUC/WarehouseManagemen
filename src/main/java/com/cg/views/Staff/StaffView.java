package com.cg.views.Staff;

import com.cg.Enum.EGender;
import com.cg.Enum.ERole;
import com.cg.model.user.Staff;
import com.cg.service.UserService.StaffService;
import com.cg.utils.AppUtils;
import com.cg.views.ListView;

import static com.cg.service.UserService.StaffService.staffList;
import static com.cg.views.Manager.ManagerView.managerMenu;
import static com.cg.views.Supplier.SupplierView.importSupplier;

public class StaffView {
    static StaffService staff1 = new StaffService();

    public static void importStaff() {
        ListView.printMenu(ListView.managerStaffListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn: ", 0, 4);
        switch (choice) {
            case 1 -> showListStaff();
            case 2 -> addStaff();
            case 3 -> editStaff();
            case 4 -> removeStaff();
            case 0 -> managerMenu();
        }
    }

    private static void showListStaff() {
        System.out.println("Danh sách nhân viên");
        int[] maxWidths = new int[7];
        for (Staff staff : staffList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(staff.getId()).length());
            maxWidths[1] = Math.max(maxWidths[1], staff.getName().length());
            maxWidths[2] = Math.max(maxWidths[2], staff.getGender().getName().length());
            maxWidths[3] = Math.max(maxWidths[3], staff.getPhone().length());
            maxWidths[4] = Math.max(maxWidths[4], staff.getAddress().length());
            maxWidths[5] = Math.max(maxWidths[5], staff.getUsername().length());
            maxWidths[6] = Math.max(maxWidths[6], staff.getRole().getName().length());
        }
        String format = "| %-2s | %-" + (maxWidths[1] + 2) + "s | %-" + (maxWidths[2] + 7)
                + "s | %-" + (maxWidths[3] + 4) + "s | %-" + (maxWidths[4] + 2)
                + "s | %-" + (maxWidths[5] + 12) + "s | %-" + (maxWidths[6]) + "s |\n";
        System.out.println("+----+-------------------+" +
                "------------+----------------+------------------------+---------------------+-----------+");
        System.out.printf(
                format
                , "id", "Tên nhân viên", "Giới tính", "Số điện thoại", "Địa chỉ", "Tài khoản đăng nhập", "Chức vụ");
        for (Staff staff : staffList) {
            System.out.printf(format
                    , staff.getId(), staff.getName(), staff.getGender().getName(), staff.getPhone()
                    , staff.getAddress(), staff.getUsername()
                    , staff.getRole().getName());
        }
        System.out.println("+----+-------------------+" +
                "------------+----------------+------------------------+---------------------+-----------+");
        importStaff();
    }

    private static void addStaff() {
        String name = AppUtils.getString("Nhập tên nhân viên");
        EGender gender = AppUtils.getIntWithBound("Nhập giới tính(1. Nam/ 2. Nữ)", 1, 2) == 1 ? EGender.MALE : EGender.FEMALE;
        String phone = AppUtils.getString("Nhập số điện thoại");
        String address = AppUtils.getString("Nhập địa chỉ");
        String userName = AppUtils.getString("Nhập tên đăng nhập");
        String pass = AppUtils.getString("Nhập mật khẩu");
        ERole role = AppUtils.getIntWithBound("Nhập chức vụ(1. Import/ 2. Sale/ 3. Inventory/ 4.Audit)", 1, 4)
                == 1 ? ERole.IMPORT : AppUtils.getIntWithBound("Nhập chức vụ(1. Import/ 2. Sale/ 3. Inventory/ 4.Audit)", 1, 4)
                == 2 ? ERole.SALES : AppUtils.getIntWithBound("Nhập chức vụ(1. Import/ 2. Sale/ 3. Inventory/ 4.Audit)", 1, 4)
                == 3 ? ERole.INVENTORY : ERole.AUDIT;
        Staff staff = new Staff(Staff.getNextId(), name, EGender.valueOf(String.valueOf(gender)), phone, address, userName, pass, ERole.valueOf(String.valueOf(role)));
        staff1.creat(staff);
        importStaff();
    }

    private static void editStaff() {
        try {
            boolean check = false;
            System.out.println("Thay đổi thông tin nhân viên");
            long id = AppUtils.getLongId("Nhập id cần thay đổi");
            if (staff1.isExist(id)) {
                Staff staff = staff1.findById(id);
                String format = "| %-4s | %-5s | %-5s | %-5s | %-25s | %-5s | %-9s | %-9s |\n";
                System.out.println("+------+---------------+" +
                        "-----------+---------------+---------------------------+---------------------+-----------+-----------+");
                System.out.printf(
                        format
                        , "id", "Tên nhân viên", "Giới tính", "Số điện thoại", "Địa chỉ", "Tài khoản đăng nhập", "Mật khẩu", "Chức vụ");

                System.out.printf("| %-4s | %-13s | %-9s | %-13s | %-25s | %-19s | %-9s | %-9s |\n"
                        //5,Huy,Nam,09999999,28ntp,huy,4321,Audit
                        , staff.getId(), staff.getName()
                        , staff.getGender().getName()
                        , staff.getPhone(), staff.getAddress()
                        , staff.getUsername(), staff.getPassword()
                        , staff.getRole().getName());
                System.out.println("+------+---------------+" +
                        "-----------+---------------+---------------------------+---------------------+-----------+-----------+");

                ListView.printMenu(ListView.editStaffList);
                do {
                    int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 7);
                    switch (choice) {
                        case 1 -> {
                            String name = AppUtils.getString("Thay đổi tên: ");
                            staff.setName(name);
                        }
                        case 2 -> {
                            EGender gender = EGender.parse(AppUtils.getString("Thay đổi giới tính(Nam/Nữ)"));
                            staff.setGender(gender);
                        }
                        case 3 -> {
                            String phone = AppUtils.getString("Thay đổi số điện thoại: ");
                            staff.setPhone(phone);
                        }
                        case 4 -> {
                            String address = AppUtils.getString("Thay đổi địa chỉ: ");
                            staff.setAddress(address);
                        }
                        case 5 -> {
                            String userName = AppUtils.getString("Thay đổi tài khoản: ");
                            staff.setUsername(userName);
                        }
                        case 6 -> {
                            String pass = AppUtils.getString("Thay đổi mật khẩu: ");
                            staff.setPassword(pass);
                        }
                        case 7 -> {
                            ERole role = ERole.parse(AppUtils.getString("Thay đổi chức vụ: "));
                            staff.setRole(role);
                        }
                        case 0 -> importSupplier();
                    }
                    staff1.edit(id, staff);
                    int loop = AppUtils.getIntWithBound("""
                            Bạn có muốn thay đổi gì nữa không?
                            1-Có
                            2-Không
                            """, 1, 2);
                    switch (loop) {
                        case 1 -> check = true;
                        case 2 -> check = false;
                    }
                } while (check);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        importStaff();
    }

    private static void removeStaff() {
        long id = AppUtils.getInt("Nhập id cần xóa");
        staff1.remove(id);
        System.out.println("Xóa thành công");
    }
}
