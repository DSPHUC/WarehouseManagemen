package com.cg.views.Product;

import com.cg.model.ProductGroup;
import com.cg.service.product.ProductGroupService;
import com.cg.utils.AppUtils;
import com.cg.views.ListView;

import static com.cg.model.ProductGroup.getNextId;
import static com.cg.service.product.ProductGroupService.productGroupList;
import static com.cg.views.Manager.ManagerView.managerMenu;

public class ProductGroupView {
    static ProductGroupService productGroup1 = new ProductGroupService();

    public static void importProductGroup() {
        ListView.printMenu(ListView.managerProductGroupListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 5);
        switch (choice) {
            case 1 -> {
                showProductGroupList();
                importProductGroup();
            }
            case 2 -> addProductGroup();
            case 3 -> editProductGroup();
            case 4 -> removeProductGroup();
            case 0 -> managerMenu();
        }
    }

    public static void showProductGroupList() {
        System.out.println("Danh sách nhóm hàng hóa");
        int[] maxWidths = new int[3];
        for (ProductGroup productGroup : productGroupList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(productGroup.getIdGroup()).length());
            maxWidths[1] = Math.max(maxWidths[1], productGroup.getName().length());
            maxWidths[2] = Math.max(maxWidths[2], productGroup.getDescription().length());
        }
        String format = "%-" + (maxWidths[0] + 1) + "s | %-" + (maxWidths[1] + 1) + "s | %-"
                + (maxWidths[2] + 1) + "s\n";
        System.out.printf(format, "Id", "Nhóm hàng hóa", "Mô tả");
        for (ProductGroup productGroup : productGroupList) {
            System.out.printf(format
                    , productGroup.getIdGroup(), productGroup.getName(), productGroup.getDescription());
        }
    }

    private static void addProductGroup() {
        String name = AppUtils.getString("Nhập tên hàng hóa: ");
        String description = AppUtils.getString("Nhập miêu tả hàng hóa: ");
        ProductGroup productGroup = new ProductGroup(getNextId(), name, description);
        productGroup1.creat(productGroup);
        importProductGroup();
    }

    private static void editProductGroup() {
        try {
            boolean check = false;
            System.out.println("Thay đổi thông tin nhóm hàng");
            long id = AppUtils.getLongId("Nhập id cần thay đổi");
            if (productGroup1.isExist(id)) {
                ProductGroup productGroup = productGroup1.findById(id);
                String format = "| %-4s | %-5s | %-5s |\n";
                System.out.println("+------+---------------+----------+");
                System.out.printf(format, "id", "Tên nhóm hàng", "Mô tả");
                System.out.printf("| %-4s | %-13s | %-9s |\n"
                        , productGroup.getIdGroup(), productGroup.getName()
                        , productGroup.getDescription());
                System.out.println("+------+---------------+-----------+");
                ListView.printMenu(ListView.editProductGroupList);
                do {
                    int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 7);
                    switch (choice) {
                        case 1 -> {
                            String name = AppUtils.getString("Thay đổi tên: ");
                            productGroup.setName(name);
                        }
                        case 2 -> {
                            String description = AppUtils.getString("Thay đổi mô tả");
                            productGroup.setDescription(description);
                        }
                        case 0 -> importProductGroup();
                    }
                    productGroup1.edit(id, productGroup);
                    int loop = AppUtils.getIntWithBound("""
                            Bạn có muốn thay đổi gì nữa không?
                            1- Có
                            2- Không
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
        importProductGroup();
    }

    private static void removeProductGroup() {
        long id = AppUtils.getInt("Nhập id cần xóa");
        productGroup1.remove(id);
        System.out.println("Xóa thành công");
        importProductGroup();
    }
}
