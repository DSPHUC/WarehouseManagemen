package com.cg.service.product;

import com.cg.Enum.EPath;
import com.cg.model.ProductCategory;
import com.cg.service.CRUD;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductCategoryService implements CRUD<ProductCategory> {

    private static final String fileProductCategory = "D:\\case-study\\Supermarket\\data\\fileProductCategory.txt";
    public static List<ProductCategory> productCategoryList;


    static {
        productCategoryList = getAll1();
    }

    public ProductCategoryService() {
        productCategoryList = getAll();
        if (productCategoryList == null) {
            productCategoryList = new ArrayList<>();
        }
    }

    public static List<ProductCategory> getAll1() {
        return FileUtils.readData(fileProductCategory, ProductCategory.class);
    }

    @Override
    public List<ProductCategory> getAll() {
        return FileUtils.readData(fileProductCategory, ProductCategory.class);
    }

    public static void save() {
        FileUtils.writeData(fileProductCategory, productCategoryList);
    }

    @Override
    public ProductCategory findById(long id) {
        ProductCategory productCategory = productCategoryList.stream()
                .filter(productCategory1
                        -> (productCategory1.getIdCategory() == id))
                .findFirst().orElse(null);
        if (productCategory == null) {
            System.out.println("Không tìm thấy ngành hàng với ID: " + id);
        }
        return productCategory;
    }

    @Override
    public ProductCategory findByName(String name) {
        ProductCategory productCategory = productCategoryList.stream()
                .filter(productCategory1 -> (Objects.equals(productCategory1.getName(), name)))
                .findFirst().orElse(null);
        if (productCategory == null) {
            System.out.println("Không tìm thấy ngành hàng với name: " + name);
        }
        return productCategory;
    }

    @Override
    public void creat(ProductCategory productCategory) {
        if (productCategoryList.stream().anyMatch(e ->
                Objects.equals(e.getName(), productCategory.getName()))) {
            System.out.println("Tên ngành hàng đã có");
            return;
        }
        productCategoryList.add(productCategory);
        save();
    }

    @Override
    public void edit(long id, ProductCategory productCategory) {
        productCategoryList.stream()
                .filter(productCategory1 -> productCategory1.getIdCategory() == id)
                .findFirst()
                .ifPresent(productCategory2 -> {
                    productCategory2.setName(productCategory.getName());
                });
        save();
    }

    @Override
    public void remove(long id) {
        productCategoryList = productCategoryList.stream().filter(staff1 ->
                        !Objects.equals(staff1.getIdCategory(), id))
                .collect(Collectors.toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        ProductCategory productCategory = productCategoryList.stream()
                .filter(e -> Objects.equals(e.getIdCategory(), id))
                .findFirst()
                .orElse(null);
        return productCategory != null;
    }

    public static ProductCategory getByName(String name) {
        return productCategoryList.stream()
                .filter(productCategory1 ->
                        productCategory1.getName().equals(name))
                .findFirst().orElse(null);
    }

}
