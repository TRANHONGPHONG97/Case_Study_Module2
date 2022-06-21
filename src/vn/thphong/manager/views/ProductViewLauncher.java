package vn.thphong.manager.views;

import java.util.Scanner;

public class ProductViewLauncher {
    private static Scanner scanner = new Scanner(System.in);

    public static void runProduct() {
        int number;
        boolean flag = true;
        try {
            do {
                ProductView productView = new ProductView();
                MainLauncher.menuProduct();
                number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        productView.addProduct();
                        runProduct();
                        break;
                    case 2:
                        productView.update();
                        break;
                    case 3:
                        SearchProduct.searchMenu();
                        break;
                    case 4:
                        productView.showProduct(InputOption.SHOW);
                        runProduct();
                        break;
                    case 5:
                        SortProduct.option();
                        break;
                    case 6:
                        productView.remove();
                        runProduct();
                        break;
                    case 7:
                        MainLauncher.menuOption();
                        break;
                    case 8:
                        System.exit(8);
                    default:
                        System.out.println("Không hợp lệ, vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            System.out.println("Nhập sai chức năng. Mời nhập lại!");
            runProduct();
        }
    }
}
