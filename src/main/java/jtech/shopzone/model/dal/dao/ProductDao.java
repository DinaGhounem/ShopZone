/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.Status;

import java.util.ArrayList;

import jtech.shopzone.model.entity.ProductsInfoEntity;

/**
 * @author Hanaa
 */
public interface ProductDao {

    ArrayList<ProductsInfoEntity> getProducts();

    ArrayList<ProductsInfoEntity> getProducts(int categoryId);

    ArrayList<ProductsInfoEntity> getProducts(double minPrice, double maxPrice);

    ArrayList<ProductsInfoEntity> getProductsBTWRange(int range);

    ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, int categoryId);

    ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, double minPrice, double maxPrice);

    Status addProduct(ProductsInfoEntity product);

    Status deleteProduct(int productId);

    Status updateProduct(ProductsInfoEntity product);

    int checkProductQuantities(int productId);

    Status updateProductQuantities(int productId, int quantities);

    ProductsInfoEntity getProductInfo(int productID);

    int getProductCount();

    double getMaxmimumPrice();

}
