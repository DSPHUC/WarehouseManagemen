package com.cg.model.ProductDetailsList;

import com.cg.model.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class  ProductDetails  implements Serializable, ParseModel<ProductDetails> {
    private Product productName;
    private int quantity;
    private int price;

    public ProductDetails() {
    }

    public ProductDetails(Product productName, int quantity, int price) {
        this.setProductName(productName); ;
        this.setQuantity(quantity); ;
        this.setPrice(price);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s",this.getProductName(),this.getQuantity(),this.getPrice());
    }

    @Override
    public ProductDetails parse(String line) {
        String[] fields = line.split(",");
        ProductDetails productDetails = null;
        try {
            Product productName = new Product(
                    Long.parseLong(fields[0]),
                    fields[1],
                    fields[2],
                    new ProductCategory(Long.parseLong(fields[3]), fields[4]),
                    new ProductGroup(Long.parseLong(fields[5]), fields[6], fields[7])
            );
            int quantity = Integer.parseInt(fields[8]);
            int price = Integer.parseInt(fields[9]);
            productDetails = new ProductDetails(productName, quantity, price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDetails;
    }


}
