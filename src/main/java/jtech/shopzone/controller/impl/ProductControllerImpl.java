package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.ProductController;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;
import jtech.shopzone.model.dal.dao.impl.ProductDaoImpl;

public class ProductControllerImpl implements ProductController {

    private ProductDao productDao;

    private ProductControllerImpl() {
        productDao = new ProductDaoImpl();
    }

    public static ProductController newInstance() {
        return new ProductControllerImpl();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(int categoryId) {
        return productDao.getProducts(categoryId);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(double minPrice, double maxPrice) {
        return productDao.getProducts(minPrice, maxPrice);
    }

    @Override
    public Status AddProduct(ProductsInfoEntity product) {
        return productDao.addProduct(product);
    }

    @Override
    public Status deleteProduct(int productId) {
        return productDao.deleteProduct(productId);
    }

    @Override
    public Status updateProduct(ProductsInfoEntity product) {
        return productDao.updateProduct(product);
    }

    @Override
    public int checkProductQuantities(int productId) {
        return productDao.checkProductQuantities(productId);
    }

    @Override
    public Status updateProductQuantities(int productId, int quantities) {
        return productDao.updateProductQuantities(productId, quantities);
    }

    @Override
    public ProductsInfoEntity getProductInfo(int productID) {
        return productDao.getProductInfo(productID);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range) {
        return productDao.getProductsBTWRange(range);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, int categoryId) {
        return productDao.getProductsBTWRange(range, categoryId);

    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, double minPrice, double maxPrice) {
        return productDao.getProductsBTWRange(range, minPrice, maxPrice);
    }

    @Override
    public int getProductCount() {
        return productDao.getProductCount();
    }

    @Override
    public double getMaxmimumPrice() {
        return productDao.getMaxmimumPrice();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange2(int range) {
        return productDao.getProductsBTWRange2(range);
    }

    @Override
    public int getProductCount2() {
        return productDao.getProductCount2();
    }

}
