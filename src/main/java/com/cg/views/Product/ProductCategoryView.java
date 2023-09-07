package com.cg.views.Product;

import com.cg.model.ProductCategory;
import com.cg.service.product.ProductCategoryService;
import com.cg.utils.AppUtils;
import com.cg.views.ListView;

import java.util.List;

import static com.cg.model.ProductGroup.getNextId;
import static com.cg.views.Manager.ManagerView.managerMenu;

public class ProductCategoryView {
    static ProductCategoryService productCategory1 = new ProductCategoryService();

    static List<ProductCategory> productCategoryList = ProductCategoryService.productCategoryList;

    public static void importProductCategory() {
        ListView.printMenu(ListView.managerProductCategoryListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 5);
        switch (choice) {
            case 1 -> {
                showProductCategoryList();
                importProductCategory();
            }
            case 2 -> {
                addProductCategory();
                importProductCategory();
            }
            case 3 -> editProductCategory();
            case 4 -> removeProductCategory();
            case 0 -> managerMenu();
        }
    }

    public static void showProductCategoryList() {
        System.out.println("Danh sáchngành hàng");
        int[] maxWidths = new int[2];
        for (ProductCategory productCategory : productCategoryList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(productCategory.getIdCategory()).length());
            maxWidths[1] = Math.max(maxWidths[1], productCategory.getName().length());
        }
        String format = "%-" + (maxWidths[0] + 1) + "s | %-" + (maxWidths[1] + 1) + "s\n";
        System.out.printf(format, "Id", "Ngành hàng ");
        for (ProductCategory productCategory : productCategoryList) {
            System.out.printf(format
                    , productCategory.getIdCategory(), productCategory.getName());
        }
    }

    private static void addProductCategory() {
        String name = AppUtils.getString("Nhập tên ngành haàng: ");
        ProductCategory productCategory = new ProductCategory(getNextId(), name);
        productCategory1.creat(productCategory);
        importProductCategory();
    }

    private static void editProductCategory() {
        try {
            boolean check = false;
            System.out.println("Thay đổi tên ngành hàng");
            long id = AppUtils.getLongId("Nhập id cần thay đổi");
            if (productCategory1.isExist(id)) {
                ProductCategory productCategory = productCategory1.findById(id);
                String format = "| %-4s | %-5s |\n";
                System.out.println("+------+---------------+");
                System.out.printf(
                        format
                        , "id", "Tên ngành hàng");
                System.out.printf("| %-4s | %-13s |\n"
                        , productCategory.getIdCategory(), productCategory.getName());
                System.out.println("+------+---------------+");
                do {
                    String name = AppUtils.getString("Thay đổi tên: ");
                    productCategory.setName(name);
                    productCategory1.edit(id, productCategory);
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
        importProductCategory();
    }

    private static void removeProductCategory() {
        long id = AppUtils.getInt("Nhập id cần xóa");
        productCategory1.remove(id);
        System.out.println("Xóa thành công");
        importProductCategory();
    }
}
