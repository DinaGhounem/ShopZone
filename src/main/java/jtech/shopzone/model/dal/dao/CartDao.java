/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.bean.ShoppingCart;
import jtech.shopzone.model.dal.Status;

import java.util.ArrayList;

import jtech.shopzone.model.entity.StockStatus;

/**
 * @author Muhammed Mahrous
 */
public interface CartDao {

    /**
     * This method is CALLED ONLY ONCE when the product is first
     * added to the cart, further calls to addProduct will reset
     * the product count to 1, so alternatively to change product quantity
     * you use updateProductQuantities()
     * @param userId
     * @param productId
     * @return
     */
    Status addProduct(int userId, int productId);

    Status deleteProduct(int userId, int productId);

    ArrayList<ShoppingCart> getUserProducts(int userId);

    Status updateProductQuantities(int userId, int productId, int quantities);

    Status checkProductExistence(int userId, int productId);

    int userItemCount(int userId);

    /**
     * gets quantity of a specific product in user's cart
     * @param userId
     * @param productId
     * @return 0 if product is not in the cart, -1 if SQLException occurred
     *         or quantity of the product in user cart
     */
    int getQuantity(int userId, int productId);

    StockStatus getStockStatus(int productId, int quantity);
}
