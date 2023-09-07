package com.cg.views;

import com.cg.model.Inventory;
import com.cg.model.Product;
import com.cg.service.InventoryService;
import com.cg.utils.AppUtils;
import com.cg.views.Product.ProductView;

import java.util.Objects;

import static com.cg.service.InventoryService.*;
import static com.cg.service.product.ProductService.productList;

public class InventoryView {
    public static InventoryService inventory1 = new InventoryService();

    public static void showInventory() {
        System.out.println("Danh sách hàng tồn kho");
        int[] maxWidths = new int[10];
        for (Inventory inventory : inventoryList) {
            maxWidths[0] = Math.max(maxWidths[0], String.valueOf(inventory.getId()).length());
            maxWidths[1] = Math.max(maxWidths[1], productList.get((int) inventory.getId()).getName().length());
            maxWidths[2] = Math.max(maxWidths[2], String.valueOf(inventory.getQuantity()).length());
        }
        String format = "%-" + (maxWidths[0] + 1) + "s | %-" + (maxWidths[1] + 1) + "s | %-"
                + (maxWidths[2] + 1) + "s |\n";
        System.out.println("+----+-------------------+" +
                "------------+");
        System.out.printf(format
                , "Id", "Tên hàng hóa", "Số lượng");
        for (Inventory inventory : inventoryList) {
            System.out.printf(format
                    , inventory.getId()
                    , productList.get((int) inventory.getProduct().getId()).getName()
                    , inventory.getQuantity()
            );
        }
        System.out.println("+----+-------------------+" +
                "------------+----------------+");

    }

    public static void updateProductsInventory(int number,String name) {
        long productIndexInInventory = checkProductIndex(name);
//        ProductView.showProductList();
        Product product = productList.get((int)productIndexInInventory);
        int quantity = CheckQuantityInventory(name) + number;
        Inventory inventory = new Inventory(productIndexInInventory, product, quantity);
        inventory1.creat(inventory);
        save();
    }



}
