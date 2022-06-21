package vn.thphong.manager.views;

import java.util.Scanner;

import static vn.thphong.manager.views.MainLauncher.menuOption;
import static vn.thphong.manager.views.MainLauncher.menuOrder;

public class OrderViewLauncher {

    static Scanner scanner = new Scanner(System.in);
    static OrderView orderView = new OrderView();

    public static void run() {
        int choice;
        do {
            menuOrder();
            try {
                System.out.println("Chọn chức năng");
                System.out.print(" ⭆ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.addOrder();
                        break;
                    case 2:
                        orderView.showAllOrder();
                        break;
                    case 3:
                        menuOption();
                        break;
                    case 4:
                        System.exit(4);
                    default:
                        System.out.println("Nhập chức năng không đúng. Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Nhập sai. Vui lòng nhập lại!");
            }
        } while (true);
    }


}
