package vn.thphong.manager.services;

import vn.thphong.manager.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    List<Product> getItem();

    void add(Product newProduct);

//    void addItem(Product newProduct);

    void addItem(Product newProduct);

    void update(Product newProduct);

    void remove(long id);

    boolean exists(int id);

    //    boolean existById(int id);
    Product findById(int id);
    void updateQuantity(long id, int quantity);


    Product getProductByID(int productID);
}
