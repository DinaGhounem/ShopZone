package jtech.shopzone.controller;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

public interface CartController {
    Status AddProduct(int userId, ProductsInfoEntity product);

    Status deleteProduct(int userId, int productId);

    ArrayList<ProductsInfoEntity> getUserProducts(int userId);

    Status updateProductQuantities(int userId, int productId, int quantities);

    int checkProductExistance(int userId, int productId);

    int userItemCount(int userId);
}
