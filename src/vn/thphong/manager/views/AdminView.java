package vn.thphong.manager.views;

import vn.thphong.manager.services.IUserService;
import vn.thphong.manager.services.UserService;
import vn.thphong.manager.utils.AppUtils;

import java.util.Scanner;

public class AdminView {
    private final IUserService userService; //Dependency Inversion Principle (SOLID)
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        userService = UserService.getInstance();
    }

    public void adminLogin() {
        boolean isRetry;
        System.out.println("✤ ✤ ✤ ✤ ✤ ✤ ✤ ✤ ✤  ĐĂNG NHẬP HỆ THỐNG  ✤ ✤ ✤ ✤ ✤ ✤ ✤ ✤ ✤ ✤");
        do {
            System.out.println("Username");
            String username = AppUtils.retryString("Username");
            System.out.println("Mật khẩu");
            String password = AppUtils.retryString("Mật khẩu");
            if (userService.adminLogin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ!");
                isRetry = isRetry();
            } else {
                System.out.println("Bạn đã đăng nhập thành công!\n");

                isRetry = false;
            }
        } while (isRetry);
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("Nhấn 'y' để đăng nhập lại     ||      Nhấn 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
                ex.printStackTrace();
            }
        } while (true);
    }
}
