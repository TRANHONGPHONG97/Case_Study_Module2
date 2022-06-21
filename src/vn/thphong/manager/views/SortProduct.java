package vn.thphong.manager.views;

import vn.thphong.manager.model.Product;
import vn.thphong.manager.services.ProductService;
import vn.thphong.manager.sort.*;

import java.util.List;
import java.util.Scanner;

public class SortProduct {
    private static Scanner scanner = new Scanner(System.in);
    static ProductView productView = new ProductView();
    static ProductService productService = new ProductService();
    static List<Product> productsList;

    public SortProduct() {
        productsList = productService.getItem();
    }

    public static void sortMenu() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤             SẮP XẾP SẢN PHẨM             ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                          ✤");
        System.out.println("✤    1. Sắp xếp theo ID sản phẩm           ✤");
        System.out.println("✤    2. Sắp xếp theo tên sản phẩm          ✤");
        System.out.println("✤    3. Sắp xếp theo số lượng sản phẩm     ✤");
        System.out.println("✤    4. Sắp xếp theo giá sản phẩm          ✤");
        System.out.println("✤    0. Quay lại                           ✤");
        System.out.println("✤                                          ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.print("Chọn chức năng:");
        System.out.print("⭆ \t");
    }

    public static void option() {
        boolean flag = true;
        int choice;
        do {
            sortMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showSortById();
                    break;
                case 2:
                    showSortByName();
                    break;
                case 3:
                    showSortByQuantity();
                    break;
                case 4:
                    showSortByPrice();
                    break;
                case 0:
                    ProductViewLauncher.runProduct();
                    break;
                default:
                    System.out.println("Không hợp lệ, vui lòng nhập lại");
                    flag = false;
            }
        } while (!flag);
    }

    public static void showSortById() {
        boolean flag = true;
        int choice;
        do {
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤          SẮP XẾP THEO ID SẢN PHẨM           ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤                                             ✤");
            System.out.println("✤      1. Theo thứ tự từ tăng dần             ✤");
            System.out.println("✤      2. Theo thứ tự từ giảm dần             ✤");
            System.out.println("✤      0. Quay lại                            ✤");
            System.out.println("✤                                             ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println();
            System.out.print("Chọn chức năng:\n");
            System.out.print("⭆ \t");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo ID tăng dần");
                        SortByIDASC sortByIDASC = new SortByIDASC();
                        productList.sort(sortByIDASC);
                        productView.show(productList);
                        option();
                        break;
                    case 2:
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo ID giảm dần");
                        SortByIDDEC sortByIDDEC = new SortByIDDEC();
                        productsList.sort(sortByIDDEC);
                        productView.show(productsList);
                        option();
                        break;
                    case 0:
                        ProductViewLauncher.runProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }

    public static void showSortByName() {
        boolean flag = true;
        int choice;
        do {
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤       SẮP XẾP THEO TÊN SẢN PHẨM          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤                                          ✤");
            System.out.println("✤       1. Theo thứ tự tên tăng dần        ✤");
            System.out.println("✤       2. Theo thứ tự tên giảm dần        ✤");
            System.out.println("✤       0. Quay lại                        ✤");
            System.out.println("✤                                          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println();
            System.out.print("Chọn chức năng :");
            System.out.print("⭆ \t");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo tên tăng dần");
                        SortByNameASC sortByNameASC = new SortByNameASC();
                        productList.sort(sortByNameASC);
                        productView.show(productList);
                        option();
                        break;
                    case 2:
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo tên giảm dần");
                        SortByNameDEC sortByNameDEC = new SortByNameDEC();
                        productsList.sort(sortByNameDEC);
                        productView.show(productsList);
                        option();
                        break;
                    case 0:
                        ProductViewLauncher.runProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }

    public static void showSortByQuantity() {
        boolean flag = true;
        int choice;
        do {
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤       SẮP XẾP THEO SỐ LƯỢNG SẢN PHẨM     ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤                                          ✤");
            System.out.println("✤     1. Theo thứ tự từ tăng dần           ✤");
            System.out.println("✤     2. Theo thứ tự từ giảm dần           ✤");
            System.out.println("✤     0. Quay lại                          ✤");
            System.out.println("✤                                          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println();
            System.out.print("Chọn chức năng:");
            System.out.print("⭆ \t");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo số lượng tăng dần");
                        SortByQuantityASC sortByQuantityASC = new SortByQuantityASC();
                        productList.sort(sortByQuantityASC);
                        productView.show(productList);
                        option();
                        break;
                    case 2:
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo số lượng giảm dần");
                        SortByQuantityDEC sortByQuantityDESC = new SortByQuantityDEC();
                        productsList.sort(sortByQuantityDESC);
                        productView.show(productsList);
                        option();
                        break;
                    case 0:
                        ProductViewLauncher.runProduct();
                        break;
                    default:
                        System.out.println("Chọn lại !");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }

    public static void showSortByPrice() {
        boolean flag = true;
        int choice;
        do {
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤       SẮP XẾP THEO GIÁ SẢN PHẨM          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println("✤                                          ✤");
            System.out.println("✤      1. Theo thứ tự từ tăng dần          ✤");
            System.out.println("✤      2. Theo thứ tự từ giảm dần          ✤");
            System.out.println("✤      0. Quay lại                         ✤");
            System.out.println("✤                                          ✤");
            System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
            System.out.println();
            System.out.print("Chọn chức năng :");
            System.out.print("⭆ \t");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo giá tăng dần");
                        SortByPriceASC sortByPriceASC = new SortByPriceASC();
                        productList.sort(sortByPriceASC);
                        productView.show(productList);
                        option();
                        break;
                    case 2:
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo giá giảm dần");
                        SortByPriceDEC sortByPriceDEC = new SortByPriceDEC();
                        productsList.sort(sortByPriceDEC);
                        productView.show(productsList);
                        option();
                        break;
                    case 0:
                        ProductViewLauncher.runProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }
}
