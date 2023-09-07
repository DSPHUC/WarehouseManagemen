package com.cg.views.Product;

import com.cg.model.Product;
import com.cg.model.ProductCategory;
import com.cg.model.ProductGroup;
import com.cg.service.product.ProductService;
import com.cg.utils.AppUtils;
import com.cg.views.InventoryView;
import com.cg.views.ListView;

import static com.cg.service.product.ProductCategoryService.productCategoryList;
import static com.cg.service.product.ProductGroupService.productGroupList;
import static com.cg.service.product.ProductService.productList;
import static com.cg.views.Manager.ManagerView.managerMenu;

public class ProductView {
    public static ProductService product1 = new ProductService();

    public static void importProduct() {
        ListView.printMenu(ListView.managerProductListMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 5);
        switch (choice) {
            case 1 -> {
                showProductList();
                importProduct();
            }
            case 2 -> addProduct();
            case 3 -> editProduct();
            case 4 -> removeProduct();
            case 5 -> InventoryView.showInventory();
            case 0 -> managerMenu();
        }
    }

    public static void showProductList() {
        System.out.println("Danh sách hàng hóa");
        int[] maxWidths = new int[5];
        for (Product product : productList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(product.getId()).length());
            maxWidths[1] = Math.max(maxWidths[1], product.getName().length());
            maxWidths[2] = Math.max(maxWidths[2], product.getDescription().length());
            maxWidths[3] = Math.max(maxWidths[3], product.getProductCategory().toString().length());
            maxWidths[4] = Math.max(maxWidths[4], product.getProductGroup().toString().length());
        }
        String format = "%-" + (maxWidths[0] + 1) + "s | %-" + (maxWidths[1] + 1) + "s | %-"
                + (maxWidths[2] + 1) + "s | %-" + (maxWidths[3] + 1) + "s | %-"
                + (maxWidths[4] + 1) + "s\n";
        System.out.println("+----+-------------------+" +
                "------------+----------------+------------------------+");
        System.out.printf(
                format
                , "Id", "Tên hàng hóa", "Mô tả", "Loại hàng", "Nhóm hàng");
        for (Product product : productList) {
            System.out.printf(format
                    , product.getId(), product.getName(), product.getDescription(), productCategoryList.get((int) product.getId()).getName()
                    , productGroupList
                            .get((int) product.getId()).getName());
        }
        System.out.println("+----+-------------------+" +
                "------------+----------------+------------------------+");
    }

    private static void addProduct() {
        String name = AppUtils.getString("Nhập tên hàng hóa: ");
        String description = AppUtils.getString("Nhập miêu tả hàng hóa: ");
        ProductCategoryView.showProductCategoryList();
        ProductCategory productCategory = productCategoryList
                .get(AppUtils.getIntWithBound(
                        "Chọn loại hàng: ", 0, productCategoryList.size() - 1));
        ProductGroupView.showProductGroupList();
        ProductGroup productGroup = productGroupList
                .get(AppUtils.getIntWithBound(
                        "Chọn nhóm hàng: ", 0, productGroupList.size() - 1));
        Product product = new Product(Product.getNextId(), name, description, productCategory, productGroup);
        product1.creat(product);
        importProduct();
    }

    private static void editProduct() {
        try {
            boolean check = false;
            System.out.println("Thay đổi thông tin hàng hóa");
            long id = AppUtils.getLongId("Nhập id cần thay đổi");
            if (product1.isExist(id)) {
                Product product = product1.findById(id);
                String format = "| %-4s | %-5s | %-5s | %-5s | %-5s |\n";
                System.out.println("+------+---------------+----------+----------+----------+");
                System.out.printf(format
                        , "id", "Tên hàng hóa", "Mô tả", "Ngành hàng", "Nhóm hàng");
                System.out.printf("| %-4s | %-13s | %-9s |\n"
                        , product.getId(), product.getName()
                        , product.getDescription()
                        , product.getProductCategory().getName()
                        , product.getProductGroup().getName()
                );
                System.out.println("+------+---------------+-----------+-----------+-----------+");
                ListView.printMenu(ListView.editProductList);
                do {
                    int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 7);
                    switch (choice) {
                        case 1 -> {
                            String name = AppUtils.getString("Thay đổi tên: ");
                            product.setName(name);
                        }
                        case 2 -> {
                            String description = AppUtils.getString("Thay đổi mô tả");
                            product.setDescription(description);
                        }
                        case 3 -> {
                            ProductCategory productCategory = productCategoryList
                                    .get(AppUtils.getIntWithBound("Thay đổi ngành hàng", 1, productCategoryList.size()));
                            product.setProductCategory(productCategory);
                        }
                        case 4 -> {
                            ProductGroup productGroup = productGroupList.get(AppUtils.getIntWithBound("Thay đổi ngành hàng", 1, productGroupList.size()));
                            product.setProductGroup(productGroup);
                        }
                        case 0 -> importProduct();
                    }
                    product1.edit(id, product);
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
        importProduct();
    }

    private static void removeProduct() {
        showProductList();
        long id = AppUtils.getInt("Nhập id cần xóa");
        product1.remove(id);
        System.out.println("Xóa thành công");
    }
}
