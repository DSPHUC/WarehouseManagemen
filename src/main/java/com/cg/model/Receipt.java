package com.cg.model;


import com.cg.Enum.ERole;
import com.cg.Enum.EType;
import com.cg.model.ProductDetailsList.ProductDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cg.service.Bill.ReceiptService.receiptList;
import static java.lang.Long.*;

@Getter
@Setter
@NoArgsConstructor
public class Receipt implements Serializable, ParseModel<Receipt> {
    private long id;
    private EType type;
    private List<ProductDetails> productDetail;

    private String nameLogin;
    private String role;
    private LocalDateTime dateTime;
    private Product productName;
    private int quantity;
    private int price;


    public Receipt(long id, EType type, List<ProductDetails> productDetail, String nameLogin, String role, LocalDateTime dateTime) {
        this.setId(id);
        this.setType(type);
        this.setProductDetail(productDetail);
        this.setNameLogin(nameLogin);
        this.setRole(role);
        this.setDateTime(dateTime);
    }


    public static long getNextId() {
        long max = 0;
        if (receiptList != null) {
            for (Receipt receipt : receiptList) {
                max = receipt.getId();

            }
            return max + 1;
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {

        return String.format("%s,%s,%s,%s,%s,%s", this.getId(), this.getType(), this.getProductDetail()
                , this.getNameLogin()
                , this.getRole(), this.getDateTime());
    }


    @Override
    public Receipt parse(String line) {
        String[] fields = line.split(",");
        Receipt receipt = null;
        try {
            //long id, EType type, List<Product> productDetail
            // , String nameLogin, String role, LocalDateTime dateTime) {

            long id = parseLong(fields[0]);
            EType eType = EType.valueOf(fields[1]);
            List<ProductDetails> productDetailList = new ArrayList<>();
            Product product = new Product(
                    Long.parseLong(fields[2].replace("[", ""))
                    , fields[3], fields[4]
                    , new ProductCategory(Long.parseLong(fields[5]), fields[6])
                    , new ProductGroup(Long.parseLong(fields[7]), fields[8], fields[9]));
            int quantity = Integer.parseInt(fields[10]);
            int price = Integer.parseInt(fields[11].replace("]", ""));

            ProductDetails productDetails = new ProductDetails(product, quantity, price);

            productDetailList.add(productDetails);

            String nameLogin = fields[fields.length-3];
            String role = (fields[fields.length-2]);
            LocalDateTime dateTime = LocalDateTime.parse(fields[fields.length - 1]);
            receipt = new Receipt(id, eType, productDetailList, nameLogin, role, dateTime);

            //
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receipt;
    }
}
