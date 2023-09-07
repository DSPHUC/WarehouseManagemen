package com.cg.service.Bill;

import com.cg.Enum.EPath;
import com.cg.model.Receipt;
import com.cg.service.CRUD;
import com.cg.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ReceiptService implements CRUD<Receipt> {
    private static final String fileReceipt = "D:\\case-study\\Supermarket\\data\\fileReceipt.txt";
    public static List<Receipt> receiptList;

    static {
        receiptList = FileUtils.readData(fileReceipt, Receipt.class);
    }

    public ReceiptService() {
        receiptList = getAll();
        if (receiptList == null) {
            receiptList = new ArrayList<>();
        }

    }

    public static void save() {
        FileUtils.writeData(fileReceipt, receiptList);
    }

    @Override
    public List<Receipt> getAll() {
        return FileUtils.readData(fileReceipt, Receipt.class);
    }

    @Override
    public Receipt findById(long id) {
        Receipt receipt = receiptList.stream()
                .filter(receipt1 -> receipt1.getId() == id)
                .findFirst().orElse(null);
        if (receipt == null) {
            System.out.println("Không tìm thấy phiếu với Id: " + id);
        }
        return receipt;
    }

    @Override
    public Receipt findByName(String name) {
        return null;
    }

    @Override
    public void creat(Receipt receipt) {
        receiptList.add(receipt);
        save();
    }

    @Override
    public void edit(long id, Receipt receipt) {
        receiptList.stream()
                .filter(receipt1 -> receipt1.getId() == id)
                .findFirst().ifPresent(receipt2 -> {
                    receipt2.setProductDetail(receipt.getProductDetail());
                    receipt2.setType(receipt.getType());
                    receipt2.setQuantity(receipt.getQuantity());
                    receipt2.setRole(receipt.getRole());
                });
        save();
    }

    @Override
    public void remove(long id) {
        receiptList = receiptList.stream().filter(receipt1 ->
                        !Objects.equals(receipt1.getId(), id))
                .collect(Collectors.toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        Receipt receipt = receiptList.stream()
                .filter(receipt1 -> Objects.equals(receipt1.getId(), id))
                .findFirst().orElse(null);
        return receipt != null;
    }


}