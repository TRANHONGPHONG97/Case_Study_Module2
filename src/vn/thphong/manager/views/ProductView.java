package vn.thphong.manager.views;

import vn.thphong.manager.model.Product;
import vn.thphong.manager.services.ProductService;
import vn.thphong.manager.utils.AppUtils;
import vn.thphong.manager.utils.CSVUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private ProductService productService = new ProductService();
    private final Scanner scanner = new Scanner(System.in);
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " đ");

    public ProductView() {
        productService = ProductService.getInstance();
    }

    private static final String path = "data/product.csv";

    public String inputNameProduct(InputOption option) {
        String nameProduct = "";
        switch (option) {
            case ADD:
                System.out.println("Nhập tên Chè: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên Chè bạn muốn sửa: ");
                break;
        }
        do {
            nameProduct = AppUtils.retryString("Tên Chè");
        } while (nameProduct.isEmpty());
        return nameProduct;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity < 0)
                System.out.println("(Số lượng phải lớn hơn 0)");
        } while (quantity < 0);
        return quantity;
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá Chè: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá Chè muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price < 0)
                System.out.println("Giá phải lớn hơn 0");
        } while (price < 0);
        return price;
    }

    public void addProduct() {
        do {
            long id = System.currentTimeMillis() / 1000;
            String nameProduct = inputNameProduct(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            double price = inputPrice(InputOption.ADD);
            Product product = new Product((int) id, nameProduct, price, quantity);
            productService.add(product);
//            productService.addItem(product);
            System.out.println("Sản phẩm đã được thêm thành công!");
        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public void update() {
        show(productService.getItem());
        System.out.print("Nhập ID cần sửa\n⭆ \t ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (productService.exists(id)) {
                MainLauncher.inputUpdate();
                boolean is = true;
                do {
                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1:
                                inputPrice(id);
                                break;
                            case 2:
                                inputQuantity(id);
                                break;
                            case 3:
                                ProductViewLauncher.runProduct();
                                break;
                            default:
                                System.out.print("Chưa hợp lệ! Mời nhập lại\n");
                                is = false;
                        }
                    } catch (Exception e) {
                        update();
                    }
                } while (!is);
                boolean flag = true;
                do {
                    System.out.print("Nhấn 'c' để tiếp tục cập nhật || Nhấn 'b' để quay lại || Nhấn 'e' để thoát\n");
                    System.out.print("⭆ \t ");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "c":
                            update();
                            break;
                        case "b":
                            ProductViewLauncher.runProduct();
                            break;
                        case "e":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Mời nhập lại!");
                            flag = false;
                    }
                } while (!flag);
            }else {
                System.out.println("Mời nhập lại!");
                update();
            }
        } catch (Exception ex) {
            System.out.println("Mời nhập lại!");
            update();
        }
    }


    private List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.readFile(path);
        for (String record : records) {
            products.add(Product.parse(record));
        }
        return products;
    }


    public void show(List<Product> productList) {
//        List<Product> productList = productService.getItem();

        System.out.println("------------------------------ DANH SÁCH CHÈ -----------------------------------------");
        System.out.println("");
        System.out.printf("%-15s %-20s %-20s %-10s", "ID", "Tên Chè", "Giá", "Số lượng");
        System.out.println("");
        for (Product product : productList) {
            System.out.printf("%-15s %-20s %-20s %-10s\n",
                    product.getProductID(),
                    product.getName(),
                    decimalFormat.format(product.getPrice()),
                    product.getQuantity());
        }
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------\n");
    }

    public void showProduct(InputOption option) {
        List<Product> productList = productService.getItem();

        System.out.println("-------------------------- DANH SÁCH CHÈ -------------------------");
        System.out.println(" ");
        System.out.printf("%-15s %-20s %-15s %-10s", "  ID", "Tên Chè", "   Giá", "Số lượng");
        System.out.println(" ");
        for (Product product : productList) {
            System.out.printf("%-15s %-20s %-15s %-10s\n",
                    product.getProductID(),
                    product.getName(),
                    decimalFormat.format(product.getPrice()),
                    product.getQuantity());
        }
        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------\n");
    }

    public void inputPrice(int id) {
        Product product = productService.getProductByID(id);
        System.out.printf("Nhập giá: \n⭆\t");
        double price = Double.parseDouble(scanner.nextLine());
        product.setPrice(price);
        productService.update(product);
        System.out.println("Cập nhật thành công!");
    }

    public void inputQuantity(int id) {
        Product product = productService.getProductByID(id);
        System.out.print("Nhập số lượng: \n⭆ \t");
        int quantity = Integer.parseInt(scanner.nextLine());
        product.setQuantity(quantity);
        productService.update(product);
        System.out.println("Cập nhật thành công!");
    }

    public int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id: ");
                break;
            case UPDATE:
                System.out.println("Nhập Id bạn muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập Id bạn muốn xóa: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = productService.exists(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id này đã tồn tại! Vui lòng nhập lại.");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy Id! Vui lòng nhập lại.");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }
    public void remove() {
        List<Product> productList = productService.getItem();
        show(productList);
        int id;
        while (!productService.exists(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy ID!");
            System.out.println("Nhấn '1' để tiếp tục || Nhấn '2' để quay lại || Nhấn '0' để thoát");
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    remove();
                    break;
                case 2:
                    return;
//                    ProductViewLauncher.runProduct();
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, vui lòng chọn lại!");
                    break;
            }
        }
        System.out.println("✤✤✤✤✤✤✤✤✤  XÁC NHẬN XÓA  ✤✤✤✤✤✤✤✤✤");
        System.out.println("✤           1. Nhấn 1 để xóa             ✤");
        System.out.println("✤           2. Nhấn 2 để quay lại        ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤ ✤");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            productService.remove(id);
            System.out.println("Đã xóa sản phẩm thành công!");
            AppUtils.isRetry(InputOption.DELETE);
        }else if (option == 2){
            ProductViewLauncher.runProduct();
        }
    }
}



