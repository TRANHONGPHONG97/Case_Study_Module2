package vn.thphong.manager.views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {
    static Scanner scanner = new Scanner(System.in);

    public MainLauncher() {
        startMainMenu();
    }

    public static void launch() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
        menuOption();
    }

    public static void menuOption() {
        do {
            mainMenu();
            try {
                System.out.println("Chọn chức năng: ");
                System.out.print(" ⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        UserViewLauncher.launch();
                        break;
                    case 2:
                        ProductViewLauncher.runProduct();
                        break;
                    case 3:
                        OrderViewLauncher.run();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai chức năng. Mời nhập lại!");
                }
            } catch (Exception io) {
                System.out.println("Nhập sai chức năng. Mời Nhập lại!\n");
            }
        } while (true);
    }

    public static void mainMenu() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤       CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI CỬA HÀNG CHÈ HUẾ      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                      MAIN MENU                       ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤                 1. Quản lý người dùng                ✤");
        System.out.println("✤                 2. Quản lý Chè                       ✤");
        System.out.println("✤                 3. Quản lý đơn hàng                  ✤");
        System.out.println("✤                 0. Thoát chương trình                ✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
    }

    public static void menuUser() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                   QUẢN LÝ NGƯỜI DÙNG                 ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤               1. Thêm người dùng                     ✤");
        System.out.println("✤               2. Sửa thông tin người dùng            ✤");
        System.out.println("✤               3. Xóa thông tin người dùng            ✤");
        System.out.println("✤               4. Hiện thông tin người dùng           ✤");
        System.out.println("✤               5. Quay lại MAIN MENU                  ✤");
        System.out.println("✤               6. Thoát chương trình                  ✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
    }

    public static void menuProduct() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                       QUẢN LÝ CHÈ                    ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤               1. Thêm sản phẩm                       ✤");
        System.out.println("✤               2. Sửa thông tin sản phẩm              ✤");
        System.out.println("✤               3. Tìm kiếm sản phẩm                   ✤");
        System.out.println("✤               4. Hiển thị danh sách sản phẩm         ✤");
        System.out.println("✤               5. Sắp xếp danh sách sản phẩm          ✤");
        System.out.println("✤               6. Xóa sản phẩm                        ✤");
        System.out.println("✤               7. Quay lại MAIN MENU                  ✤");
        System.out.println("✤               8. Thoát chương trình                  ✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("Chọn chức năng");
        System.out.printf("⭆ \t");
    }

    public static void startMainMenu() {
        try {
            boolean flag = true;
            do {
                String num = scanner.nextLine();
                switch (num) {
                    case "1":
                        ProductViewLauncher.runProduct();
                        break;
                    case "2":
//                        OrderViewLauncher.menuOrder();
                        break;
                    default:
                        System.out.println("Không hợp lệ, xin vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (InputMismatchException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void inputUpdate() {
        System.out.println("✤✤✤✤✤✤    Update    ✤✤✤✤✤✤");
        System.out.println("✤                             ✤");
        System.out.println("✤    1. Cập nhật giá          ✤");
        System.out.println("✤    2. Cập nhật số lượng     ✤");
        System.out.println("✤    3. Quay lại              ✤");
        System.out.println("✤                             ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("Chọn chức năng");
        System.out.printf("⭆ \t");
    }
    public static void menuOrder() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                   QUẢN LÝ ĐƠN HÀNG                   ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤                1. Tạo đơn hàng                       ✤");
        System.out.println("✤                2. Xem danh sách đơn hàng             ✤");
        System.out.println("✤                3. Quay lại MAIN MENU                 ✤");
        System.out.println("✤                4. Thoát chương trình                 ✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
    }
}
