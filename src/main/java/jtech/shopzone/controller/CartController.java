package jtech.shopzone.controller;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

public interface CartController {
    Status addProduct(int userId, int productId);

    Status deleteProduct(int userId, int productId);

    ArrayList<CartEntity> getUserProducts(int userId);

    Status updateProductQuantities(int userId, int productId, int quantities);

    Status checkProductExistance(int userId, int productId);

    int userItemCount(int userId);
}
