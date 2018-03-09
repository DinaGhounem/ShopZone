package jtech.shopzone.controller;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;
import jtech.shopzone.model.entity.StockStatus;

import java.util.ArrayList;
/**
 * @author Mahrous
 */
public interface CartController {

    Status addProduct(int userId, int productId);

    Status deleteProduct(int userId, int productId);

    ArrayList<CartEntity> getUserProducts(int userId);

    Status updateProductQuantities(int userId, int productId, int quantities);

    Status checkProductExistance(int userId, int productId);

    int userItemCount(int userId);

    Status restCart(int userId);

    int getQuantity(int userId, int productId);

    StockStatus getStockStatus(int productId, int quantity);
}
