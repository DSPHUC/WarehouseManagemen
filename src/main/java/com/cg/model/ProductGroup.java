package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.cg.service.product.ProductGroupService.productGroupList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroup implements Serializable, ParseModel<ProductGroup> {
    long idGroup;
    String name;
    String description;

    @Override
    public String toString() {
        return String.format("%s,%s,%s",this.getIdGroup(),this.getName(),this.getDescription());
    }

    @Override
    public ProductGroup parse(String line) {
        String[] fields = line.split(",");
        ProductGroup productGroup = null;
        try {
            productGroup = new ProductGroup(Long.parseLong(fields[0]), fields[1], fields[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productGroup;
    }
    public static long getNextId() {
        long max = 0;
        if ( productGroupList!= null) {
            for (ProductGroup productGroup : productGroupList) {
                if (productGroup.getIdGroup() > max) {
                    max = productGroup.getIdGroup();
                }
            }
        }
        return max + 1;
    }
}
