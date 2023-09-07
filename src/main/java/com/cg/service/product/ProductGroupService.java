package com.cg.service.product;

import com.cg.Enum.EPath;
import com.cg.model.ProductGroup;
import com.cg.service.CRUD;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductGroupService implements CRUD<ProductGroup> {
    private static final String fileProductGroup ="D:\\case-study\\Supermarket\\data\\" +
            "fileProductGroup.txt";
    public static List<ProductGroup> productGroupList;
    static {
        productGroupList = getAll1();
    }
    public ProductGroupService() {
        productGroupList = getAll();
        if (productGroupList == null) {
            productGroupList = new ArrayList<>();
        }
    }
    public static List<ProductGroup> getAll1() {
        return FileUtils.readData(fileProductGroup, ProductGroup.class);

    }
    public static void save() {
        FileUtils.writeData(fileProductGroup, productGroupList);
    }
    @Override
    public List<ProductGroup> getAll() {
        return FileUtils.readData(fileProductGroup, ProductGroup.class);

    }

    @Override
    public ProductGroup findById(long id) {
        ProductGroup productGroup = productGroupList.stream()
                .filter(productGroup1 -> (productGroup1.getIdGroup() == id))
                .findFirst().orElse(null);
        if (productGroup == null) {
            System.out.println("Không tìm thấy nhóm hàng với ID: " + id);
        }
        return productGroup;
    }

    @Override
    public ProductGroup findByName(String name) {
        ProductGroup productGroup = productGroupList.stream()
                .filter(productGroup1 -> (productGroup1.getName().equals(name)))
                .findFirst().orElse(null);
        if (productGroup == null) {
            System.out.println("Không tìm thấy nhóm hàng với ID: " + name);
        }
        return productGroup;
    }

    @Override
    public void creat(ProductGroup productGroup) {
        if (productGroupList.stream().anyMatch(e ->
                Objects.equals(e.getName(), productGroup.getName()))) {
            System.out.println("Tên nhóm hàng đã có");
            return;
        }
        productGroupList.add(productGroup);
        save();
    }

    @Override
    public void edit(long id, ProductGroup productGroup) {
        productGroupList.stream()
                .filter(productGroup1 -> productGroup1.getIdGroup() == id)
                .findFirst()
                .ifPresent(productGroup2 -> {
                    productGroup2.setName(productGroup.getName());
                    productGroup2.setDescription(productGroup.getDescription());
                });
        save();
    }

    @Override
    public void remove(long id) {
        productGroupList = productGroupList.stream().filter(productGroup1 ->
                        !Objects.equals(productGroup1.getIdGroup(), id))
                .collect(Collectors.toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        ProductGroup productGroup = productGroupList.stream()
                .filter(e -> Objects.equals(e.getIdGroup(), id))
                .findFirst()
                .orElse(null);
        return productGroup != null;
    }
    public static ProductGroup getByName(String name) {
        return productGroupList.stream()
                .filter(productGroup1 ->
                        productGroup1.getName().equals(name))
                .findFirst().orElse(null);
    }
}
