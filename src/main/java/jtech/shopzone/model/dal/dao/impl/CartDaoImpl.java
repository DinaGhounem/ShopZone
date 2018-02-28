package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CartDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CartDaoImpl implements CartDao {
    @Override
    public Status AddProduct(int userId, ProductsInfoEntity product) {
        return null;
    }

    @Override
    public Status deleteProduct(int userId, int productId) {
        return null;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getUserProducts(int userId) {
        return null;
    }

    @Override
    public Status updateProductQuantities(int userId, int productId, int quantities) {
        return null;
    }

    @Override
    public int checkProductExistance(int userId, int productId) {
        return 0;
    }

    @Override
    public int userItemCount(int userId) {
        return 0;
    }
}
