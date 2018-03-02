package jtech.shopzone.controller;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

public interface ProductController {
    ArrayList<ProductsInfoEntity> getProducts();

    ArrayList<ProductsInfoEntity> getProducts(int categoryId);

    ArrayList<ProductsInfoEntity> getProducts(double minPrice,double maxPrice);

    Status AddProduct(ProductsInfoEntity product);

    Status deleteProduct(int productId);

    Status updateProduct(ProductsInfoEntity product);

    int checkProductQuantities(int productId);

    Status updateProductQuantities(int productId, int quantities);
}
