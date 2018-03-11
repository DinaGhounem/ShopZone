package jtech.shopzone.controller;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

public interface ProductController {

    ArrayList<ProductsInfoEntity> getProducts();

    ArrayList<ProductsInfoEntity> getProducts(int categoryId);

    ArrayList<ProductsInfoEntity> getProducts(double minPrice, double maxPrice);

    Status AddProduct(ProductsInfoEntity product);

    Status deleteProduct(int productId);

    Status updateProduct(ProductsInfoEntity product);

    int checkProductQuantities(int productId);

    Status updateProductQuantities(int productId, int quantities);

    ProductsInfoEntity getProductInfo(int productID);

    ArrayList<ProductsInfoEntity> getProductsBTWRange(int range);

    ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, int categoryId);

    ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, double minPrice, double maxPrice);

    int getProductCount();

    double getMaxmimumPrice();

    ArrayList<ProductsInfoEntity> getProductsBTWRange2(int range);

    public int getProductCount2();
}
