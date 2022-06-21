package vn.thphong.manager.sort;

import vn.thphong.manager.model.Product;

import java.util.Comparator;

public class SortByIDASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getProductID() - o2.getProductID());
    }
}
