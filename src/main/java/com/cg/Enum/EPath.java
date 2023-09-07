package com.cg.Enum;

public enum EPath {
    MANAGER("D:\\case-study\\Supermarket\\data\\" +
            "fileManager.txt"),
    STAFF("D:\\case-study\\Supermarket\\data\\" +
            "staffList.txt"),
    RECEIPT("D:\\case-study\\Supermarket\\data\\fileReceipt.txt"),
    PRODUCTCATEGORY("D:\\case-study\\Supermarket\\data\\fileProductCategory.txt"),
    PRODUCTGROUP("D:\\case-study\\Supermarket\\data\\" +
            "fileProductGroup.txt"),
    PRODUCT("D:\\case-study\\Supermarket\\data\\" +
            "fileProduct.txt"),
    INVENTORY("D:\\case-study\\Supermarket\\data\\Inventory.txt"),
    SUPPLIER("D:\\case-study\\Supermarket\\data\\" +
            "supplierList.txt");
    private final String filePath;

    EPath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
