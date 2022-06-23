package vn.thphong.manager.services;

import vn.thphong.manager.model.Product;
import vn.thphong.manager.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    List<Product> products = new ArrayList<>();
    private static final String path = "data/product.csv";
    private static ProductService instance;
    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }
//    @Override
//    public List<Product> findAll() {
//        return null;
//    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.readFile(path);
        for (String record : records) {
            products.add(Product.parse(record));
        }
        return products;
    }

    @Override
    public List<Product> getItem() {
        List<Product> newProducts = new ArrayList<>();
        List<String> records = CSVUtils.readFile(path);
        for (String ignored : records) {
            newProducts.add(Product.parse(ignored));
        }
        return newProducts;
    }
    @Override
    public void add(Product newProduct) {
        List<Product> products = getItem();
        products.add(newProduct);
        CSVUtils.writeFile(path, products);

    }


    @Override
    public void addItem(Product newProduct) {
        List<Product> products = getItem();
        products.add(newProduct);
        CSVUtils.writeFile(path, products);
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = getItem();
        for (Product product : products) {
            if (product.getProductID() == newProduct.getProductID()) {
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
            }
        }
        CSVUtils.writeFile(path, products);
    }


    @Override
    public void remove(long id) {
        List<Product> products = getItem();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == id) {
                products.remove(i);
                break;
            }
        }
        CSVUtils.writeFile(path, products);
    }

    public Product getProductByID(int id) {
        List<Product> products = getItem();
        for (Product product : products) {
            if (product.getProductID() == id) {
                return product;
            }
        }
        return null;
    }
//    @Override
//    public boolean existById(int id) {
//        List<Product> products = findAll();
//        for (Product product : products) {
//            if (product.getProductID() == id)
//                return true;
//        }
//        return false;
//    }
    @Override
    public boolean exists(int id) {
        return getProductByID(id) != null;
    }

    @Override
    public Product findById(int id) {
        List<Product> products = findAll();
        for (Product product : products){
            if(id == product.getProductID()){
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateQuantity(long id, int quantity) {
        List<Product> products = findAll();
        for (Product product : products) {
            if(product.getProductID() == id){
                if(product.getQuantity()>=quantity){
                    product.setQuantity(product.getQuantity() - quantity);
                    CSVUtils.writeFile(path,products);
                    break;
                }
            }
        }
    }

}


