package vn.thphong.manager.views;

import vn.thphong.manager.model.*;
import vn.thphong.manager.services.*;
import vn.thphong.manager.utils.AppUtils;
import vn.thphong.manager.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final Scanner scanner = new Scanner(System.in);

    private final IProductService productService;

    private final IOrderService orderService;

    private final IOrderItemService orderItemService;

    public OrderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        orderItemService = OrderItemService.getInstance();
    }

    public OrderItem addOrderItems(long orderId) {
        orderItemService.findAll();
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập Id sản phẩm muốn mua");
        System.out.print(" ⭆ ");
        int idAcs = Integer.parseInt(scanner.nextLine());
        while (!productService.exists(idAcs)) {
            System.out.println("Id không tồn tại!");
            System.out.println("Nhập Id sản phẩm");
            System.out.print(" ⭆ ");
            idAcs = scanner.nextInt();
        }
        Product product = productService.findById(idAcs);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng muốn mua");
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity < 0)
                System.out.println("(Số lượng phải lớn hơn 0)");
        } while (quantity < 0);
        while (!checkQualityTea(product, quantity)) {
            System.out.println("Nhập sai số lượng. Vui lòng nhập lại!");
            System.out.println("Nhập số lượng");
            System.out.print(" ⭆ ");
            quantity = scanner.nextInt();
        }
        String nameAcs = product.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);

        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, idAcs, nameAcs, total);
        productService.updateQuantity(idAcs, quantity);
        return orderItem;
    }

    public boolean checkQualityTea(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void addOrder() {
        try {
            long orderId = System.currentTimeMillis() / 1000;
            ProductView productView = new ProductView();
            productView.showProduct(InputOption.ADD);
            System.out.println("✤✤✤✤✤✤✤✤   Creat Order    ✤✤✤✤✤✤✤✤");
            System.out.println("✤                                       ✤");
            System.out.println("✤        1. Tạo order mua sản phẩm      ✤");
            System.out.println("✤        2. Quay lại                    ✤");
            System.out.println("✤        3. Thoát chương trình          ✤");
            System.out.println("✤                                       ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("Nhập chức năng");
            System.out.print(" ⭆ ");
            int choiceA = Integer.parseInt(scanner.nextLine());
            switch (choiceA) {
                case 1:
                    System.out.println("Nhập họ và tên (vd: Tran Hong Phong) ");
                    System.out.print(" ⭆ ");
                    String name = scanner.nextLine();
                    while (!ValidateUtils.isFullNameValid(name)) {
                        System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                        System.out.println("Nhập tên (vd: Tran Hong Phong) ");
                        System.out.print(" ⭆ ");
                        name = scanner.nextLine();
                    }
                    System.out.println("Nhập số điện thoại");
                    System.out.print(" ⭆ ");
                    String phone = scanner.nextLine();
                    while (!ValidateUtils.isPhoneValid(phone)) {
                        System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                        System.out.println("Nhập số điện thoại (vd: 03556929116)");
                        System.out.print(" ⭆ ");
                        phone = scanner.nextLine();
                    }
                    String address;
                    do {
                        System.out.println("Nhập địa chỉ");
                        System.out.print(" ⭆ ");
                        address = scanner.nextLine();
                        if (address.trim().isEmpty()) {
                            System.out.println("Địa chỉ không được để trống!");
                            System.out.println("Nhập lại địa chỉ: ");
                            System.out.print(" ⭆ ");
                            address = scanner.nextLine();
                        }
                    } while (address.trim().isEmpty());

                    OrderItem orderItem = addOrderItems(orderId);
                    Order order = new Order(orderId, name, phone, address);
                    orderItemService.add(orderItem);
                    orderService.add(order);

                    System.out.println("Tạo đơn hàng thành công!");
                    do {
                        System.out.println("✤✤✤✤✤✤✤✤✤✤     Order     ✤✤✤✤✤✤✤✤✤✤");
                        System.out.println("✤                                          ✤ ");
                        System.out.println("✤     1. Nhấn 'y' để tiếp tục tạo đơn      ✤");
                        System.out.println("✤     2. Nhấn 'q' để quay lại              ✤");
                        System.out.println("✤     3. Nhấn 'p' để in hóa đơn            ✤");
                        System.out.println("✤     4. Nhấn 't' để thoát                 ✤");
                        System.out.println("✤                                          ✤");
                        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
                        System.out.print(" ⭆ ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "y":
                                addOrder();
                                break;
                            case "q":
                                OrderViewLauncher.run();
                                break;
                            case "p":
                                showPaymentInfo(orderItem, order);
                                break;
                            case "t":
                                AppUtils.exit();
                                break;
                            default:
                                System.out.println("Nhập không hợp lệ. Vui lòng nhập lại!");
                        }
                    } while (true);
                case 2:
                    OrderViewLauncher.run();
                    break;
                case 3:
                    AppUtils.exit();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Nhập sai. Vui lòng nhập lại!");
            addOrder();
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("-------------------------------------------------------     HOÁ ĐƠN BÁN LẺ      ---------------------------------------------------");
            System.out.printf("%-15s %-20s %-15s %-15s %-30s %-15s %-15s\n", "   ID", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên Chè", "Số lượng", "Giá");
            System.out.printf("%-15d %-20s %-15s %-15s %-30s %-15d %-15s \n",
                    order.getId(),
                    order.getFullName(),
                    order.getMobile(),
                    order.getAddress(),
                    orderItem.getProductName(),
                    orderItem.getQuantity(),
                    AppUtils.doubleToVND(orderItem.getPrice())
            );
            System.out.println("\nTổng tiền cần thanh toán là: " + AppUtils.doubleToVND(orderItem.getPrice_tong()));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhập 'q' để trở lại ||  Nhập 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không đúng. Vui lòng nhập lại!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = orderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        double totals = 0;
        try {
            System.out.println("-------------------------------------------------------------------- ORDER ----------------------------------------------------------------------------");
            System.out.println("                                                                                                                                      ");
            System.out.printf("%-15s %-20s %-25s %-15s %-30s %-15s %-15s %-15s \n",
                    "   Id",
                    "Tên khách hàng",
                    " SĐT",
                    "Địa chỉ",
                    "Tên sản phẩm",
                    "Số lượng",
                    "Giá",
                    "Tổng");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-25s %-15s %-30s %-15d %-15s %-15s \n",
                        order.getId(),
                        order.getFullName(),
                        order.getMobile(),
                        order.getAddress(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                        AppUtils.doubleToVND(newOrderItem.getPrice()),
                        AppUtils.doubleToVND(newOrderItem.getPrice_tong())
                );
                totals += newOrderItem.getPrice_tong();
            }
            System.out.println("");
            System.out.println("Tổng doanh thu: " + totals + " đ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhập 'q' để trở lại || Nhập 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không đúng. Vui lòng nhập lại!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Nhập sai. Vui lòng nhập lại!");
        }
    }
}


