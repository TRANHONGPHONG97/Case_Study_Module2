package vn.thphong.manager.views;

import java.util.Scanner;

import static vn.thphong.manager.views.MainLauncher.menuUser;

public class UserViewLauncher {
    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        int option = -1;
        do {
            menuUser();
            try {
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print(" ⭆ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 6 || option < 1)
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                } while (option > 6 || option < 1);

                switch (option) {
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.deleteUser();
                        menuUser();
                        break;
                    case 4:
                        userView.showUsers(InputOption.SHOW);
                        break;
                    case 5:
                        MainLauncher.menuOption();
                        break;
                    case 6:
                        System.exit(6);
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (option != 6);
    }
}
