package com.cg.views;

import java.util.Arrays;
import java.util.List;

public class ListView {
    public static List<String> loginList = Arrays.asList("\nWelcome to Supermarket",
            "1. Login",
            "0. Exit");
    public static List<String> managerListMenu = Arrays.asList(/*"Welcome Manager" ,*/
            "1. Quản lý phiếu",//** >>  quản lí phiếu
            "2. Quản lý nhân viên",//**
            "3. Quản lý ngành hàng",
            "4. Quản lý nhóm hàng\"",
            "5. Quản lý hàng hóa",
            "6. Quản lý nhà cung cấp",
            "7.  Xem thông tin hàng hóa",//**
            "0. Back");
    public static List<String> InOutMenu=Arrays.asList(
            "1. Lập phiếu nhập",
            "2. Lập phiếu xuất",
            "0. Quay lại"
    );
    public static List<String> PrepareImportAndExportSlipsMenu = Arrays.asList(
            "1. Lập phiếu",
            "2. Xem phiếu",
            "3. Xóa phiếu",
            "0. Quay lại"
    );
    public static List<String> remoDetailMenu=Arrays.asList(
      "1. Xóa phiếu nhập kho",
      "2. Xóa phiếu xuất kho",
      "0. Back"
    );
    public static List<String> showDetailIn_OutputMenu =Arrays.asList(
            "1. Danh sách phiếu nhập kho",
            "2. Danh sách phiếu xuất kho",
            "3. Xem chi tiết phiếu",
            "0. Back"
    );
    public static List<String> managerStaffListMenu = Arrays.asList("\nQuản lý nhân viên",
            "1. Xem danh sách nhân viên",
            "2. Thêm nhân viên",
            "3. Sửa thông tin nhân viên",
            "4. Xóa nhân viên",
            "0. Back");
    public static List<String> managerProductCategoryListMenu = Arrays.asList("\nQuản lý ngành hàng",
            "1. Xem danh sách ngành hàng",
            "2. Thêm ngành hàng",
            "3. Sửa ngành hàng",
            "4. Xóa ngành hàng",
            "0. Back");
    public static List<String> managerProductGroupListMenu = Arrays.asList("\nQuản lý nhóm hàng",
            "1. Xem danh sách nhóm hàng",
            "2. Thêm nhóm hàng",
            "3. Sửa nhóm hàng",
            "4. Xóa nhóm hàng",
            "0. Back");
    public static List<String> managerProductListMenu = Arrays.asList("\nQuản lý hàng hóa",
            "1. Xem danh sách hàng hóa",
            "2. Thêm hàng hóa mới",
            "3. Sửa thông tin hàng hóa",
            "4. Xóa hàng hóa",
            "5. Kiểm tra hàng tồn",
            "0. Back");
    public static List<String> managerVendorListMenu = Arrays.asList("\nQuản lý nhà cung cấp",
            "1. Xem danh sách nhà cung cấp",
            "2. Thêm nhà cung cấo",
            "3. Sửa thông tin nhà cung cấp",
            "4. Xóa nhà cung cấp",
            "0. Back");
    public static List<String> editSuplierList = Arrays.asList(
            "1. Sửa tên nhà cung cấp",
            "2. Sửa địa chỉ nhà cung cấp",
            "3. Sửa số điện thoại nhà cung cấp",
            "4. Sửa email nhà cung cấp",
            "0. Back"
    );
    public static List<String> editProductList = Arrays.asList(
            "1. Sửa tên hàng hóa",
            "2. Sửa mô tả hàng hóa",
            "3. Sửa ngành hàng",
            "4. Sửa nhóm hàng",
            "0. Back"
    );
    public static List<String> editProductGroupList = Arrays.asList(
            "1. Sửa tên nhóm hàng",
            "2. Sửa mô tả nhóm hàng",
            "0. Back"
    ); public static List<String> editProductCategoryList = Arrays.asList(
            "1. Sửa tên ngành hàng",
            "2. Sửa mô tả ngành hàng",
            "0. Back"
    );
    public static List<String> editStaffList = Arrays.asList(

            "1. Sửa tên nhân viên",
            "2. Sửa giới tính",
            "3. Sửa số điện thoại",
            "4. Sửa địa chỉ",
            "5. Sửa tên đăng nhập",
            "6, Sửa mật khẩu",
            "7. Thay đổi chức vụ",
            "0. Back"
    );
    public static List<String> importStaffListMenu = Arrays.asList("\nWelcome import staff",
            "1. Nhập hàng",
            "2. Lập phiếu nhập hàng",
            "0. Back");
    public static List<String> salesStaffListMenu = Arrays.asList("\nWelcome sales staff",
            "1. Lập hóa đơn",
            "2. Thông tin hàng hóa",
            "3. Kiểm tra hàng tồn",
            "0. Back");
    public static List<String> inventoryStaffListMenu = Arrays.asList("\nWelcome inventory staff",
            "1. Xem thông tin phiếu nhập và xuất",
            "2. Kiểm tra hàng tồn",
            "0. Back");
    public static List<String> auditStaffListMenu = Arrays.asList("\nWelcome audit staff",
            "1. Lập báo cáo thống kê",
            "2. Xem thông tin các phiếu nhập và xuất",
            "3. Kiểm tra hàng tồn",
            "0. Back");

    public static void printMenu(List<String> menu) {
        for (String str : menu) {
            System.out.println(str);
        }
    }
}
