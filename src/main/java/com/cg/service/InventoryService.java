package com.cg.service;

import com.cg.Enum.EPath;
import com.cg.model.Inventory;
import com.cg.model.Product;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.cg.service.product.ProductService.productList;
import static com.cg.views.InventoryView.inventory1;
import static com.cg.views.InventoryView.showInventory;

public class InventoryService implements CRUD<Inventory> {
    private static final String fileInventory = "D:\\case-study\\Supermarket\\data\\Inventory.txt";
    public static List<Inventory> inventoryList;

    static {
        inventoryList = FileUtils.readData(fileInventory, Inventory.class);
    }

    public InventoryService() {
        inventoryList = getAll();
        if (inventoryList == null) {
            inventoryList = new ArrayList<>();
        }
    }

    public static void save() {
        FileUtils.writeData(fileInventory, inventoryList);

    }

    @Override
    public List<Inventory> getAll() {
        return FileUtils.readData(fileInventory, Inventory.class);

    }

    @Override
    public Inventory findById(long id) {
        Inventory inventory = inventoryList.stream()
                .filter(inventory1 -> inventory1.getId() == id)
                .findFirst().orElse(null);
        if (inventory == null) {
            System.out.println("Hàng không có ");
        }
        return inventory;
    }

    @Override
    public Inventory findByName(String name) {
        Inventory inventory = inventoryList.stream()
                .filter(inventory1 -> inventory1.getProduct().getName() == name)
                .findFirst().orElse(null);
        if (inventory == null) {
            System.out.println("Hàng không có hoặc đã hết");
        }
        return inventory;
    }

    @Override
    public void creat(Inventory inventory) {
        if (inventoryList.size() != 0) {
            if (inventoryList.stream().anyMatch(e ->
                    Objects.equals(e.getProduct().getName(), inventory.getProduct().getName()))) {
                System.out.println("Mặt hàng đã có");
                return;
            }
        }
        inventoryList.add(inventory);
        save();
    }

    @Override
    public void edit(long id, Inventory inventory) {
        inventoryList.stream()
                .filter(inventory1 -> inventory1.getProduct().getId() == id)
                .findFirst()
                .ifPresent(inventory2 -> {
                    inventory2.setQuantity(inventory.getQuantity());
                });
        save();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public boolean isExist(long id) {
        return false;
    }

    public static long checkProductIndex(String productName) {
        for (int i = 0; i < inventoryList.size(); i++) {
            if (Objects.equals(inventoryList.get(i).getProduct().getName(), productName)){
                return i;
            }
        }
        return -1;
    }

    public static boolean checkExistName(String productName) {

        for (Inventory inventory : inventoryList) {
            if (Objects.equals(productName.toLowerCase(), inventory.getProduct().getName().toLowerCase())) {
                return true;
            }
        }
        for (Product product : productList) {
            if (product != null && Objects.equals(productName.toLowerCase(), product.getName().toLowerCase())) {
                return true;
            }
        }
        System.out.printf("Sản phẩm %s không có trong kho.\n", productName);
        showInventory();
        return false;
    }

    public static boolean checkExistQuantity(int quantity) {
        for (Inventory inventory : inventoryList) {
            if (quantity <= inventory.getQuantity()) {
                return true;
            }
        }
        return false;
    }

    public static int CheckQuantityInventory(String productName) {
        int total = 0;
        for (Inventory inventory : inventoryList) {
            if (inventory.getProduct().equals(productName)) {
                return inventory.getQuantity();
            }
        }
        return 0;
    }
}
