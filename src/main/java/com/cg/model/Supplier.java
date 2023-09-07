package com.cg.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.cg.service.SupplierService.supplierList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier implements Serializable,ParseModel<Supplier> {
    private long id;
    private String name;
    private String address;
    private String phone;
    private String email;

    @Override
    public Supplier parse(String line) {
        String[] items = line.split(",");
        Supplier s = null;
        try {
             s = new Supplier(Long.parseLong(items[0]), items[1], items[2], items[3], items[4]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.id, this.name, this.address, this.phone, this.email);
    }

    public static long getNextId() {
        long max = 0;
        if (supplierList != null) {
            for (Supplier supplier : supplierList) {
                if (supplier.getId() > max) {
                    max = supplier.getId();
                }
            }
        }
        return max + 1;
    }

}

