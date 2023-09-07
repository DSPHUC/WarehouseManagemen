package com.cg.model;

import java.io.Serializable;

import static com.cg.service.product.ProductService.productList;


public class Product implements Serializable, ParseModel<Product> {
    long id;
    String name;
    String description;
    ProductCategory productCategory;
    ProductGroup productGroup;

    public Product(long id, String name, String description, ProductCategory productCategory, ProductGroup productGroup) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setProductCategory(productCategory);
        this.setProductGroup(productGroup);
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.getId(), this.getName(), this.getDescription()
                , this.getProductCategory(), this.getProductGroup());
    }

    @Override
    public Product parse(String line) {
        String[] fields = line.split(",");
        Product product = null;
            try {
                product = new Product(
                    Long.parseLong(fields[0])
                    , fields[1], fields[2]
                    , new ProductCategory(Long.parseLong(fields[3]),fields[4])
                    , new ProductGroup(Long.parseLong(fields[5]),fields[6],fields[7]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public static long getNextId() {
        long max = 0;
        if (productList != null) {
            for (Product product : productList) {
                if (product.getId() > max) {
                    max = product.getId();
                }
            }
        }
        return max + 1;
    }

}
