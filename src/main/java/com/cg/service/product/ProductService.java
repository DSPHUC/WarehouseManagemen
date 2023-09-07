package com.cg.service.product;

import com.cg.Enum.EPath;
import com.cg.model.Product;
import com.cg.service.CRUD;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductService implements CRUD<Product> {
    private static final String fileProduct ="D:\\case-study\\Supermarket\\data\\" +
            "fileProduct.txt";
    public static List<Product> productList;

    static {
        productList = FileUtils.readData(fileProduct, Product.class);
    }


    public ProductService() {
        productList = getAll();
        if (productList == null) {
            productList = new ArrayList<>();
        }
    }



    public static void save() {
        FileUtils.writeData(fileProduct, productList);
    }

    @Override
    public List<Product> getAll() {
        return  FileUtils.readData(fileProduct,Product.class);

    }

    @Override
    public Product findById(long id) {
        Product product = productList.stream()
                .filter(product1 -> (product1.getId() == id))
                .findFirst().orElse(null);
        if (product == null) {
            System.out.println("Không tìm thấy hàng với ID: " + id);
        }
        return product;
    }

    @Override
    public Product findByName(String name) {
        Product product = productList.stream()
                .filter(product1 -> (product1.getName() == name))
                .findFirst().orElse(null);
        if (product == null) {
            System.out.println("Không tìm thấy hàng với ID: " + name);
        }
        return product;
    }

    @Override
    public void creat(Product product) {
        if (productList.size() != 0) {
            if (productList.stream().anyMatch(e ->
                    Objects.equals(e.getName(), product.getName()))) {
                System.out.println("Tên hàng đã có");
                return;
            }
        }
        productList.add(product);
        save();
    }

    @Override
    public void edit(long id, Product product) {
        productList.stream()
                .filter(product1 -> product1.getId() == id)
                .findFirst()
                .ifPresent(product2 -> {
                    product2.setName(product.getName());
                    product2.setDescription(product.getDescription());
                    product2.setProductCategory(product.getProductCategory());
                    product2.setProductGroup(product.getProductGroup());
                });
        save();
    }

    @Override
    public void remove(long id) {
        productList = productList.stream().filter(product1 ->
                        !Objects.equals(product1.getId(), id))
                .collect(Collectors.toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        Product product = productList.stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
        return product != null;
    }

    public static Product getByName(String name) {
        return productList.stream()
                .filter(product1 ->
                        product1.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static int getIndexByName(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if(Objects.equals(productList.get(i).getName(), name)){
                return i;
            }
        }
        return -1;
    }
}
