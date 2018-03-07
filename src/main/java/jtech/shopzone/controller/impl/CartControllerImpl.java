package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.CartController;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CartDao;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;
import jtech.shopzone.model.dal.dao.impl.CartDaoImpl;
import jtech.shopzone.model.entity.StockStatus;

/**
 * @author Mahrous
 */
public class CartControllerImpl implements CartController {

    private CartDao cartDao;

    private CartControllerImpl() {
        cartDao = new CartDaoImpl();
    }

    public static CartController newInstance() {
        return new CartControllerImpl();
    }

    @Override
    public Status addProduct(int userId, int productId) {
        return cartDao.addProduct(userId, productId);
    }

    @Override
    public Status deleteProduct(int userId, int productId) {
        return cartDao.deleteProduct(userId, productId);
    }

    @Override
    public ArrayList<CartEntity> getUserProducts(int userId) {
        return cartDao.getUserProducts(userId);
    }

    @Override
    public Status updateProductQuantities(int userId, int productId, int quantities) {
        return cartDao.updateProductQuantities(userId, productId, quantities);
    }

    @Override
    public Status checkProductExistance(int userId, int productId) {
        return cartDao.checkProductExistance(userId, productId);
    }

    @Override
    public int userItemCount(int userId) {
        return cartDao.userItemCount(userId);
    }

    @Override
    public Status restCart(int userId) {
        return cartDao.restCart(userId);
    }

    @Override
    public int getQuantity(int userId, int productId) {
        return cartDao.getQuantity(userId,productId);
    }

    @Override
    public StockStatus getStockStatus(int productId, int quantity) {
        return cartDao.getStockStatus(productId,quantity);
    }
}
