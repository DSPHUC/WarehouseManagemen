package com.cg.model;

import java.io.Serializable;

import static com.cg.service.product.ProductCategoryService.productCategoryList;


public class ProductCategory implements Serializable, ParseModel<ProductCategory> {
    long idCategory;
    String name;

    public ProductCategory() {
    }

    public ProductCategory(long idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", this.getIdCategory(), this.getName());
    }

    @Override
    public ProductCategory parse(String line) {
        String[] fields = line.split(",");
        ProductCategory productCategory = null;
        try {
            productCategory = new ProductCategory(Long.parseLong(fields[0]), fields[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productCategory;
    }

    public static long getNextId() {
        long max = 0;
        if (productCategoryList != null) {
            for (ProductCategory productCategory : productCategoryList) {
                if (productCategory.getIdCategory() > max) {
                    max = productCategory.getIdCategory();
                }
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {

    }
}
