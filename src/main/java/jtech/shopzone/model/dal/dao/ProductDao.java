/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.Status;

import java.util.ArrayList;
import jtech.shopzone.model.dal.bean.ProductsInfo;

import jtech.shopzone.model.entity.ProductsInfoEntity;

/**
 * @author Muhammed Mahrous and Dina
 */
public interface ProductDao {

    ArrayList<ProductsInfo> getProducts();

    ArrayList<ProductsInfo> getProducts(int categoryId);

    ArrayList<ProductsInfo> getProducts(double minPrice, double maxPrice);

    ArrayList<ProductsInfo> getProductsBTWRange(int range);

    ArrayList<ProductsInfo> getProductsBTWRange(int range, int categoryId);

    ArrayList<ProductsInfo> getProductsBTWRange(int range, double minPrice, double maxPrice);

    Status addProduct(ProductsInfo product);

    Status deleteProduct(int productId);

    Status updateProduct(ProductsInfo product);

    int checkProductQuantities(int productId);

    Status updateProductQuantities(int productId, int quantities);

    ProductsInfo getProductInfo(int productID);

    int getProductCount();

    double getMaxmimumPrice();

    ArrayList<ProductsInfo> getProductsBTWRange2(int range);

    public int getProductCount2();
}
