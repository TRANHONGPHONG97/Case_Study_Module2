package vn.thphong.manager.sort;

import vn.thphong.manager.model.Product;

import java.util.Comparator;

public class SortByQuantityDEC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getQuantity() - o1.getQuantity();
    }
}
