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
        boolean isNumber = true;
        int number = -1;
        do {
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤         TÌM KIẾM SẢN PHẨM          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤       1. Tìm kiếm theo ID          ✤");
            System.out.println("✤       0. Quay lại                  ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println();
            System.out.println("Chọn chức năng");
            System.out.printf("⭆ \t");
            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
            }
            switch (number) {
                case 1:
                    searchById();
                    break;
                case 0:
                    ProductViewLauncher.runProduct();
                    isNumber = false;
                    break;
                default:
                    System.out.println("Chưa hợp lệ! Mời Nhập Lại!!!");
            }

        } while (isNumber);
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

    public static void showReturnSearch(int count) {
        char choice = ' ';
        boolean isChoice;
        System.out.println();
        do {
            System.out.print("Nhấn 'q' để quay lại.");
            try {
                choice = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case 'q': {
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
