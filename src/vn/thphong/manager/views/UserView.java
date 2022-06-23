package vn.thphong.manager.views;

import vn.thphong.manager.model.Role;
import vn.thphong.manager.model.User;
import vn.thphong.manager.services.IUserService;
import vn.thphong.manager.services.UserService;
import vn.thphong.manager.utils.AppUtils;
import vn.thphong.manager.utils.InstantUtils;
import vn.thphong.manager.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = UserService.getInstance();
    }

    public void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();
                User user = new User(id, username, password, fullName, phone, email, address, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Đã thêm người dùng thành công!");
            } catch (Exception e) {
                System.out.println("Nhập sai. Vui lòng nhập lại!");
            }
        } while (AppUtils.isRetry(InputOption.ADD));
    }


    public void setRole(User user) {
        System.out.println("= =  SET ROLE = =");
        System.out.println("=    1. USER    =");
        System.out.println("=    2. ADMIN   =");
        System.out.println("= = = = = = = = =");
        System.out.println("Chọn Role: ");
        System.out.print(" ⭆ ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng. Vui lòng nhập lại!");
                setRole(user);
        }
    }

    public void showUsers(InputOption inputOption) {
        System.out.println("------------------------------------------------------------- DANH SÁCH NGƯỜI DÙNG ----------------------------------------------------------------");
        System.out.printf("%-15s %-22s %-15s %-22s %-18s %-15s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf("%-15d %-22s %-15s %-22s %-18s %-15s %-20s %-20s\n",
                    user.getId(),
                    user.getFullName(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreatedAt()),
                    user.getUpdatedAt() == null ? "" : InstantUtils.instantToString(user.getUpdatedAt())
            );
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUsers(InputOption.UPDATE);
                int id = inputId(InputOption.UPDATE);
                System.out.println("= =     SỬA NGƯỜI DÙNG    = =");
                System.out.println("=    1. Sửa tên             =");
                System.out.println("=    2. Sửa số điện thoại   =");
                System.out.println("=    3. Sửa địa chỉ         =");
                System.out.println("=    4. Quay lại            =");
                System.out.println("= = = = = = = = = = = = = = =");

                int option = AppUtils.retryChoose(1, 4);
                User newUser = new User();
                newUser.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullName(InputOption.UPDATE);
                        newUser.setFullName(name);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi tên thành công!");
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setMobile(phone);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi số điện thoại thành công!");
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi địa chỉ thành công!");
                        break;
                }
                isRetry = option != 4 && AppUtils.isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Nhập sai. Vui lòng nhập lại!");
            }
        } while (isRetry);
    }

    public void deleteUser() {
        showUsers(InputOption.DELETE);
        int id;
        while (!userService.existById(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy người dùng cần xóa!");
            System.out.println("Nhấn 'y' để tìm lại người dùng || 'q' để quay lại || 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Nhập chức năng không đúng! Vui lòng Nhập lại.");
                    break;

            }
        }
        System.out.println("✤✤✤✤✤✤✤✤✤  XÁC NHẬN XÓA  ✤✤✤✤✤✤✤✤✤");
        System.out.println("✤           1. Nhấn 1 để xóa             ✤");
        System.out.println("✤           2. Nhấn 2 để quay lại        ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤ ✤");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            userService.deleteById(id);
            System.out.println("Đã xóa người dùng thành công!");
            showUsers(InputOption.DELETE);
            AppUtils.isRetry(InputOption.DELETE);
        }
    }

    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id: ");
                break;
            case UPDATE:
                System.out.println("Nhập id bạn muốn sửa: ");
                break;
            case DELETE:
                System.out.print("Nhập Id: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = userService.existById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id này đã tồn tại. Vui lòng nhập lại!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy Id. Vui lòng nhập lại!");
                    }
                    isRetry = !exist;
                    break;
                case DELETE:
                    if (!exist) {
                        System.out.println("Không tìm thấy Id. Vui lòng nhập lại!");
                    }
            }
        } while (isRetry);
        return id;
    }

    public String inputUsername() {
        System.out.println("Nhập Username (không bao gồm dấu cách, kí tự đặc biệt)");
        System.out.print(" ⭆ ");
        String username;

        do {
            if (!ValidateUtils.isUsernameValid(username = AppUtils.retryString("Username"))) {
                System.out.println(username + " của bạn không đúng định dạng. Vui lòng kiểm tra và nhập lại!");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByUsername(username)) {
                System.out.println("Username này đã tồn tại. Vui lòng nhập lại!");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return username;
    }

    private String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập họ và tên (vd: Tran Hong Phong)");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa đổi: ");
                break;
        }

        System.out.print(" ⭆ ");
        String fullName;
        while (!ValidateUtils.isFullNameValid(fullName = scanner.nextLine())) {
            System.out.println("Tên " + fullName + "không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
            System.out.println("Nhập tên (vd: Phong Tran) ");
            System.out.print(" ⭆ ");
        }
        return fullName;
    }

    public String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số điện thoại (vd: 0987654321): ");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại mà bạn muốn đổi: ");
                break;
        }
        System.out.print(" ⭆ ");
        String mobile;
        do {
            mobile = scanner.nextLine();
            if (!ValidateUtils.isPhoneValid(mobile)) {
                System.out.println("Số " + mobile + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (vd: 0987654321)");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByPhone(mobile)) {
                System.out.println("Số này đã tồn tại. Mời bạn nhập lại!");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return mobile;
    }

    private String inputEmail() {
        System.out.println("Nhập email (vd: phong@gmail.com)");
        System.out.print(" ⭆ ");
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = scanner.nextLine())) {
                System.out.println("Email " + email + "của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
                System.out.println("Nhập email (vd: phong@gmail.com)");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByEmail(email)) {
                System.out.println("Email " + email + "của bạn đã tồn tại! vui lòng kiểm tra lại");
                System.out.println("Nhập email (vd: phong@gmail.com)");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return email;
    }

    private String inputPassword() {
        System.out.println("Nhập mật khẩu( mật khẩu phải > 8 kí tự )");
        System.out.print(" ⭆ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            System.out.println("Mật khẩu yếu. Vui lòng nhập lại! ");
            System.out.print(" ⭆ ");
        }
        return password;
    }

    private String inputAddress(InputOption option) {
            switch (option) {
                case ADD:
                    String address;
                    System.out.println("Nhập địa chỉ (vd: Huế)");
                    do {
                        address = scanner.nextLine();
                        if (address.trim().isEmpty()) {
                            System.out.println("Địa chỉ không được để trống!");
                            System.out.println("Nhập lại địa chỉ: ");
                            System.out.print(" ⭆ ");
                            address = scanner.nextLine();
                        }
                    } while (address.trim().isEmpty());
                    return address;
                case UPDATE:
                    System.out.println("Nhập địa chỉ (vd: Huế)");
                    do {
                        address = scanner.nextLine();
                        if (address.trim().isEmpty()) {
                            System.out.println("Địa chỉ không được để trống!");
                            System.out.println("Nhập lại địa chỉ: ");
                            System.out.print(" ⭆ ");
                            address = scanner.nextLine();
                        }
                    } while (address.trim().isEmpty());
                    return address;
            }

            return null;
    }
}

