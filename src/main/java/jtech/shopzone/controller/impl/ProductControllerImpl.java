package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.ProductController;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

public class ProductControllerImpl implements ProductController {
    private ProductDao productDao;

    private ProductControllerImpl() {
        // TODO init productDao from its factory method
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
    public ArrayList<ProductsInfoEntity> getProducts(Double price) {
        return productDao.getProducts(price);
    }

    @Override
    public Status AddProduct(ProductsInfoEntity product) {
        return productDao.AddProduct(product);
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
        return productDao.updateProductQuantities(productId,quantities);
    }
}
