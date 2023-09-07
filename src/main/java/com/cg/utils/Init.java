package com.cg.utils;

import com.cg.Enum.EGender;
import com.cg.Enum.ERole;
import com.cg.model.*;
import com.cg.model.user.*;
import com.cg.service.InventoryService;
import com.cg.service.SupplierService;
import com.cg.service.UserService.*;
import com.cg.service.product.ProductCategoryService;
import com.cg.service.product.ProductGroupService;
import com.cg.service.product.ProductService;
import com.cg.views.Product.ProductCategoryView;

import java.util.Arrays;



public class Init {
    public static void init() {
        if (ManagerService.managers == null) {
            initManager();
        }
        if (SupplierService.supplierList == null) {
            initSupplier();
        }
        if (StaffService.staffList == null) {
            initStaff();
        }
        if (ProductCategoryService.productCategoryList == null) {
            initProductCategory();
        }
        if (ProductGroupService.productGroupList == null) {
            initProductGroup();
        }
        if (ProductService.productList == null) {
            initProduct();
        }
        if (InventoryService.inventoryList == null) {
            initInventory();
        }
    }

    private static void initProductGroup() {
        ProductGroup productGroup1 = new ProductGroup();
        productGroup1.setIdGroup(0L);
        productGroup1.setName("Thực phẩm tươi sống");
        productGroup1.setDescription("rau củ quả, thịt, hải sản, sữa và sản phẩm từ sữa, trứng,...");

        ProductGroup productGroup2 = new ProductGroup();
        productGroup2.setIdGroup(1L);
        productGroup2.setName("Điện thoại di động");
        productGroup2.setDescription("thết bị liên lạc");

        ProductGroup productGroup3 = new ProductGroup();
        productGroup3.setIdGroup(2L);
        productGroup3.setName("Bánh kẹo");
        productGroup3.setDescription("Đa dạng về hương vị, hình dạng và chất lượng");

        ProductGroup productGroup4 = new ProductGroup();
        productGroup4.setIdGroup(3L);
        productGroup4.setName("Thịt và cá đóng hộp");
        productGroup4.setDescription("Dạng sản phẩm thực phẩm được đóng gói trong hộp");

        ProductGroupService.productGroupList = Arrays.asList(productGroup1, productGroup2, productGroup3, productGroup4);
        ProductGroupService.save();
    }

    private static void initProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setIdCategory(0L);
        productCategory1.setName("Thực Phẩm");

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setIdCategory(1L);
        productCategory2.setName("Đồ uống");

        ProductCategory productCategory3 = new ProductCategory();
        productCategory3.setIdCategory(2L);
        productCategory3.setName("Mỹ phẩm");

        ProductCategory productCategory4 = new ProductCategory();
        productCategory4.setIdCategory(3L);
        productCategory4.setName("Đồ điện tử và công nghệ");

        ProductCategory productCategory5 = new ProductCategory();
        productCategory5.setIdCategory(4L);
        productCategory5.setName("Thời trang và phụ kiện");

        ProductCategory productCategory6 = new ProductCategory();
        productCategory6.setIdCategory(5L);
        productCategory6.setName("Đồ chơi và đồ dùng trẻ em");

        ProductCategory productCategory7 = new ProductCategory();
        productCategory7.setIdCategory(6L);
        productCategory7.setName("Đồ thể thao và ngoại khóa");

        ProductCategoryService.productCategoryList = Arrays.asList(productCategory1
                , productCategory2, productCategory3, productCategory4, productCategory5
                , productCategory6, productCategory7);
        ProductCategoryService.save();
    }

    private static void initProduct() {

        Product product1 = new Product();
        product1.setId(0L);
        product1.setName("Thịt lợn xay");
        product1.setDescription("Thịt lợn tươi được xay nhuyễn đóng hộp");
        product1.setProductCategory(ProductCategoryService.productCategoryList.get(0));
        product1.setProductGroup(ProductGroupService.productGroupList.get(0));


        ProductService.productList = Arrays.asList(product1/*, product2, product3*/);
        ProductService.save();
    }

    private static void initStaff() {
        Staff staff1 = new Staff();
        staff1.setId(0L);
        staff1.setName("Phạm Văn Đạt");
        staff1.setGender(EGender.findById(1));
        staff1.setPhone("044444444");
        staff1.setAddress("28 Nguyễn Tri Phương");
        staff1.setUsername("dat");
        staff1.setPassword("1234");
        staff1.setRole(ERole.findById(2));

        Staff staff2 = new Staff();
        staff2.setId(1L);
        staff2.setName("Bùi Quang Tuấn");
        staff2.setGender(EGender.findById(1));
        staff2.setPhone("0222222222");
        staff2.setAddress("28 Nguyễn Tri Phương");
        staff2.setUsername("tuananh");
        staff2.setPassword("1234");
        staff2.setRole(ERole.findById(1));

        Staff staff3 = new Staff();
        staff3.setId(2L);
        staff3.setName("Nguyễn Ngọc Duy");
        staff3.setGender(EGender.findById(1));
        staff3.setPhone("0111111111");
        staff3.setAddress("28 Nguyễn Tri Phương");
        staff3.setUsername("duy");
        staff3.setPassword("1234");
        staff3.setRole(ERole.findById(3));

        Staff staff4 = new Staff();
        staff4.setId(3L);
        staff4.setName("Phùng Anh Tuấn");
        staff4.setGender(EGender.findById(1));
        staff4.setPhone("03333333");
        staff4.setAddress("28 Nguyễn Tri Phương");
        staff4.setUsername("tuanem");
        staff4.setPassword("1234");
        staff4.setRole(ERole.findById(4));

        StaffService.staffList = Arrays.asList(staff1, staff2, staff3, staff4);
        StaffService.save();
    }


    private static void initSupplier() {
        Supplier supplier1 = new Supplier();
        supplier1.setId(0L);
        supplier1.setName("Zara");
        supplier1.setAddress("191 Bà Triệu-phường Lê Đại Hành-quận Hai Bà Trưng-Hà Nội-Việt Nam");
        supplier1.setPhone("(+84) 246.253.5024");
        supplier1.setEmail("lh@tinphonglogistics.com");

        Supplier supplier2 = new Supplier();
        supplier2.setId(1L);
        supplier2.setName("Bitis");
        supplier2.setAddress("22 Lý Chiêu Hoàng-Phường 10-Quận 6-TP. Hồ Chí Minh-Việt Nam");
        supplier2.setPhone("(028) 38 753 443");
        supplier2.setEmail("lh@chamsockhachhang@bitis.com.vn");

        SupplierService.supplierList = Arrays.asList(supplier1, supplier2);
        SupplierService.save();
    }

    public static void initManager() {
        Manager manager = new Manager();
        manager.setId(0L);
        manager.setName("Đồng Sĩ Phúc");
        manager.setGender(EGender.findById(1));
        manager.setPhone("0999999999");
        manager.setAddress("28 Nguyễn Tri Phương");
        manager.setUsername("phuc");
        manager.setPassword("1234");
        manager.setRole(ERole.findById(0));
        ManagerService.managers = Arrays.asList(manager);
        ManagerService.save();
    }

    public static void initInventory() {
        Inventory inventory1= new Inventory();
        inventory1.setId(0L);
        inventory1.setProduct(ProductService.productList.get(0));
        inventory1.setQuantity(100);

        InventoryService.inventoryList = Arrays.asList(inventory1);
        InventoryService.save();
    }

    public static void main(String[] args) {

        init();

    }

}
