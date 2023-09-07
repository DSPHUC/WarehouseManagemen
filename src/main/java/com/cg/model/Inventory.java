package com.cg.model;

import java.io.Serializable;

import static com.cg.service.InventoryService.inventoryList;

public class Inventory implements Serializable, ParseModel<Inventory> {
    private long id;
    private Product product;
    private int quantity;


    public Inventory() {
    }

    public Inventory(
            long id, Product product
            , int quantity) {
        this.setId(id);
        this.setProduct(product);
        this.setQuantity(quantity);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.getId()
                , this.getProduct(), this.getQuantity()

        );
    }

    @Override
    public Inventory parse(String line) {
        String[] strings = line.split(",");
        Inventory inventory = null;
        try {
            inventory = new Inventory(
                    Long.parseLong(strings[0])
                    , new Product(Long.parseLong(strings[1])
                    , strings[2], strings[3]
                    , new ProductCategory(Long.parseLong(strings[4]), strings[5])
                    , new ProductGroup(Long.parseLong(strings[6]), strings[7], strings[8]))
                    , Integer.parseInt(strings[9])
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventory;
    }

    public static long getNextId() {
        long max = 0;
        if (inventoryList != null) {
            for (Inventory inventory : inventoryList) {
                if (inventory.getId() > max) {
                    max = inventory.getId();
                }
            }
        }
        return max + 1;
    }



}
