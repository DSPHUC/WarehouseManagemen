package com.cg.service;

import com.cg.Enum.EPath;
import com.cg.model.Supplier;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplierService implements CRUD<Supplier> {
    private static final String fileSupplier = "D:\\case-study\\Supermarket\\data\\" +
            "supplierList.txt";
    public static List<Supplier> supplierList;
    static {
        supplierList= getAll1();
    }

    public SupplierService() {
        supplierList = getAll();
        if (supplierList == null) {
            supplierList = new ArrayList<>();
        }
    }

    public static void save() {
        FileUtils.writeData(fileSupplier,supplierList);
    }

    @Override
    public Supplier findById(long id) {
        supplierList = getAll();
        Supplier supplier = supplierList.stream()
                .filter(supplier1 -> (supplier1.getId() == id))
                .findFirst().orElse(null);
        if (supplier == null) {
            System.out.println("Không tìm thấy supplier tương ứng với ID: " + id);
        }
        return supplier;
    }

    @Override
    public Supplier findByName(String name) {
        supplierList = getAll();
        Supplier supplier = supplierList.stream()
                .filter(supplier1 -> (Objects.equals(supplier1.getName(),name)))
                .findFirst().orElse(null);
        if (supplier == null) {
            System.out.println("Không tìm thấy Supplier với name: " + name);
        }
        return supplier;
    }

    @Override
    public void creat(Supplier supplier) {
        supplierList=getAll();
        if (supplierList.stream().anyMatch(e ->
                Objects.equals(e.getName(), supplier.getName()))) {
            System.out.println("Supplier đã có");
            return;
        }
        supplierList.add(supplier);
        save();
    }

    @Override
    public void edit(long id,Supplier supplier) {
        supplierList=getAll();
        supplierList.stream().filter(supplier1 -> supplier1.getId()==id)
                .findFirst().map(supplier2 ->{
                    supplier2.setName(supplier.getName());
                    supplier2.setPhone(supplier.getPhone());
                    supplier2.setAddress(supplier.getAddress());
                    supplier2.setEmail(supplier.getEmail());
                    return supplier2;
                } );
        save();
    }

    @Override
    public void remove(long id) {
        supplierList=getAll();
        supplierList = supplierList.stream().filter(supplier ->
                        !Objects.equals(supplier.getId(), id))
                .collect(Collectors.toList());
        save();
    }


    @Override
    public List<Supplier> getAll() {
        return FileUtils.readData(fileSupplier,Supplier.class);
    }
    public static List<Supplier> getAll1() {
        return FileUtils.readData(fileSupplier,Supplier.class);
    }
    @Override
    public boolean isExist(long id) {
        Supplier supplier = supplierList.stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
        return supplier != null;
    }
}
