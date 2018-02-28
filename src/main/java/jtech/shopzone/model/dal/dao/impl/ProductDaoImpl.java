package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao{
    @Override
    public ArrayList<ProductsInfoEntity> getProducts() {
        return null;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(int categoryId) {
        return null;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(Double price) {
        return null;
    }

    @Override
    public Status AddProduct(ProductsInfoEntity product) {
        return null;
    }

    @Override
    public Status deleteProduct(int productId) {
        return null;
    }

    @Override
    public Status updateProduct(ProductsInfoEntity product) {
        return null;
    }

    @Override
    public int checkProductQuantities(int productId) {
        return 0;
    }

    @Override
    public Status updateProductQuantities(int productId, int quantities) {
        return null;
    }
}
