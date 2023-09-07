package com.cg.views.Bill;

import com.cg.Enum.EType;
import com.cg.model.Product;
import com.cg.model.ProductCategory;
import com.cg.model.ProductDetailsList.ProductDetails;
import com.cg.model.ProductGroup;
import com.cg.model.Receipt;
import com.cg.service.Bill.ReceiptService;
import com.cg.utils.AppUtils;
import com.cg.views.ListView;
import com.cg.views.Product.ProductCategoryView;
import com.cg.views.Product.ProductGroupView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.cg.service.AuthService.name_role;
import static com.cg.service.Bill.ReceiptService.receiptList;
import static com.cg.service.InventoryService.CheckQuantityInventory;
import static com.cg.service.InventoryService.checkExistName;
import static com.cg.service.product.ProductCategoryService.productCategoryList;
import static com.cg.service.product.ProductGroupService.productGroupList;
import static com.cg.service.product.ProductService.*;
import static com.cg.views.InventoryView.*;
import static com.cg.views.Manager.ManagerView.managerMenu;
import static com.cg.views.Product.ProductView.product1;

public class ReceiptView {
    static ReceiptService receipt1 = new ReceiptService();

    public static void importReceipt() {
        ListView.printMenu(ListView.PrepareImportAndExportSlipsMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 3);
        switch (choice) {
            case 1 -> addDetail();
            case 2 -> showDetailIn_Output();
            case 3 -> {
                System.out.println("Awaiting Upgrade........");
                // xóa phiếu
                importReceipt();
            }
            case 0 -> managerMenu();
        }
    }

    public static void addDetail() {
        ListView.printMenu(ListView.InOutMenu);
        int choose = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 2);
        switch (choose) {
            case 1 -> receiptInput();
            case 2 -> {
                System.out.println("Lập phiếu xuất kho");
                System.out.println("Awaiting Upgrade........");
                importReceipt();
            }
            case 0 -> importReceipt();
        }
    }

    public static void showDetailIn_Output() {
        ListView.printMenu(ListView.showDetailIn_OutputMenu);
        int choice = AppUtils.getIntWithBound("Nhập lựa chọn", 0, 3);
        switch (choice) {
            case 1 -> {
                showDetailReceiptInput();
                showDetailIn_Output();
            }
            case 2 -> {
                System.out.println("Awaiting Upgrade........");
                showDetailIn_Output();
            }
            case 3 -> {
                System.out.println("Awaiting Upgrade........");
                ListView.printMenu(ListView.remoDetailMenu);
                showDetailIn_Output();
            }
            case 0 -> importReceipt();
        }
    }

    public static void showDetailReceiptInput() {
        long id = AppUtils.getLongId("Nhập id phiếu cần xem");
        Receipt receipt = receipt1.findById(id);
        System.out.printf("Tên phiếu %s\n", ((receipt.getDateTime())));
        System.out.printf("Loại phiếu %s\n", receipt.getType());
        System.out.printf("%s,%s,%s", receipt.getProductName().getName()
                , receipt.getQuantity()
                , receipt.getPrice());
        System.out.printf("%s,%s,%s", "", "", receipt.getQuantity() * receipt.getPrice());
    }

    public static void showReceiptInputList() {
        System.out.println("Phiếu nhập kho");
        int[] maxWidths = new int[6];
        for (Receipt receipt : receiptList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(receipt.getId()).length());
            maxWidths[1] = Math.max(maxWidths[1], receipt.getDateTime().toString().length());
            maxWidths[2] = Math.max(maxWidths[2], 15);
            maxWidths[3] = Math.max(maxWidths[3], receipt.getRole().length());
            maxWidths[4] = Math.max(maxWidths[4], receipt.getNameLogin().length());
            maxWidths[5] = Math.max(maxWidths[5], String.valueOf(receipt.getPrice()).length());
        }
        String format = "%-" + (maxWidths[0] + 1) + "s | %-" + (maxWidths[1] + 1) + "s | %-"
                + (maxWidths[2] + 1) + "s | %-" + (maxWidths[3] + 1) + "s| %-" + (maxWidths[4] + 1) + "s | %-"
                + (maxWidths[5] + 1) + "s\n";
        System.out.println("+----+-------------------+" +
                "------------+----------------+------------------------+");
        System.out.printf(
                format
                , "Id", "Tên phiếu nhập", "Loại phiếu", "Role", "Người lập phiếu", "Tổng giá hàng");
        for (Receipt receipt : receiptList) {
            System.out.printf(format
                    , receipt.getId(), receipt.getDateTime()
                    , EType.INPUT.getName(), receipt.getRole()
                    , receipt.getNameLogin()
                    , receipt.getPrice() * receipt.getQuantity()
            );
        }
    }

    public static void receiptInput() {
        System.out.println("Lập phiếu nhập kho");
        LocalDateTime time = AppUtils.getDateTime("Nhập thời gian lập phiếu");
        boolean check = false;
        long idReceipt = Receipt.getNextId();
        Receipt receipt;
        List<Product> products = new ArrayList<>();
        List<ProductDetails> productDetails1 = new ArrayList<>();
        do {
            String strProduct = AppUtils.getString("Nhập tên hàng hóa: ");
            if (!checkExistName(strProduct)) {
                addProduct(strProduct);
            }
            int quantity = AppUtils.getInt("Nhập số lượng nhập kho") + CheckQuantityInventory(strProduct);
            int price = AppUtils.getInt("Nhập giá nhập");
            int productIndex = getIndexByName(strProduct);
            Product productName = productList.get(productIndex);
            products.add(productName);
            ProductDetails productDetails = new ProductDetails(productName, quantity, price);
            productDetails1.add(productDetails); //
            updateProductsInventory(quantity, strProduct);
            int loop = AppUtils.getIntWithBound("""
                    Bạn có muốn thêm hàng gì nữa không?
                    1- Có
                    2- Không
                    """, 1, 2);
            switch (loop) {
                case 1 -> check = true;
                case 2 -> check = false;
            }
        } while (check);
        String name = name_role.get(0);
        String role = name_role.get(1);
        receipt = new Receipt(idReceipt, EType.INPUT, productDetails1, name, role, time);
        receipt1.creat(receipt);
        importReceipt();
    }

    private static void addProduct(String str) {
        System.out.println("Tạo sản phẩm mới");
        System.out.println(str);
        String description = AppUtils.getString("Nhập miêu tả hàng hóa: ");
        ProductCategoryView.showProductCategoryList();
        ProductCategory productCategory = productCategoryList
                .get(AppUtils.getIntWithBound(
                        "Chọn loại hàng: ", 0, productCategoryList.size() - 1));
        ProductGroupView.showProductGroupList();
        ProductGroup productGroup = productGroupList
                .get(AppUtils.getIntWithBound(
                        "Chọn nhóm hàng: ", 0, productGroupList.size() - 1));
        Product product = new Product(Product.getNextId(), str, description, productCategory, productGroup);
        product1.creat(product);
    }
}
