package vn.thphong.manager.views;

import vn.thphong.manager.model.Product;
import vn.thphong.manager.services.ProductService;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class SearchProduct {
    static Scanner scanner = new Scanner(System.in);
    static ProductView productView = new ProductView();
    static ProductService productService = new ProductService();
    static DecimalFormat forMater = new DecimalFormat("###,###,###" + "đ");

    public static void searchMenu() {

        productView.show(productService.getItem());
        boolean isChoice = true;
        int choice = -1;
        do {
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤         TÌM KIẾM SẢN PHẨM          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤                                    ✤");
            System.out.println("✤       1. Tìm kiếm theo ID          ✤");
            System.out.println("✤       2. Tìm kiếm theo tên         ✤");
            System.out.println("✤       0. Quay lại                  ✤");
            System.out.println("✤                                    ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println();
            System.out.println("Chọn chức năng");
            System.out.printf("⭆ \t");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
            }
            switch (choice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByName();
                    break;
                case 0:
                    ProductViewLauncher.runProduct();
                    isChoice = false;
                    break;
                default:
                    System.out.println("Chưa hợp lệ! Mời Nhập Lại!!!");
            }

        } while (isChoice);
    }

    public static void searchById() {
        List<Product> productList = productService.getItem();
        int count = 0;
        System.out.println();
        System.out.print("Nhập ID sản phẩm cần tìm kiếm: ");
        try {
            int search = Integer.parseInt(scanner.nextLine());
            System.out.println("Kết quả:  '" + search + "' là : ");
            System.out.printf("%-10s %-30s %-18s %-10s", "Id", "Tên Sản Phẩm", "Giá", "Số lượng\n");
            for (Product product : productList) {
                if (product.getProductID() == search) {
                    count++;
                    System.out.printf("%-10s %-30s %-18s %-10s\n", product.getProductID(), product.getName(), forMater.format(product.getPrice()),
                            product.getQuantity());
                }
            }
            showReturnSearch(count);

        } catch (Exception e) {
            System.out.println("Chưa hợp lệ!Mời nhập lại");
        }
    }

    public static void searchByName() {
        List<Product> productList = productService.getItem();
        int count = 0;
        System.out.println();
        System.out.print("Nhập tên sản phẩm cần tìm kiếm: ");
        String search = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm của từ khóa '" + search + "' là  ");
        search = search.toLowerCase();
        System.out.printf("%-8s %-20s %-8s %-20s\n", "ID", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "GIÁ (VND)");
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(search)) {
                count++;
                System.out.printf("%-8s %-20s %-8s %-20s\n", product.getProductID(), product.getName(),
                        product.getQuantity(), forMater.format(product.getPrice()));
            }
        }
        showReturnSearch(count);
    }

    public static void showReturnSearch(int count) {
        System.out.println("Có '" + count + "' sản phẩm được tìm thấy !");
        char press = ' ';
        boolean isChoice;
        System.out.println();
        do {
            System.out.print("Nhấn 'c' để về menu tìm kiếm !");
            try {
                press = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                press = ' ';
            }
            switch (press) {
                case 'r':
                case 'c': {
                    SearchProduct.searchMenu();
                    isChoice = false;
                    break;
                }
                default:
                    isChoice = true;
            }
        } while (isChoice);
    }
}
