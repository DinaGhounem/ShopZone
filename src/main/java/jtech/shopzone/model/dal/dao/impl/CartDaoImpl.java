package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.model.dal.bean.ProductsInfo;
import jtech.shopzone.model.dal.bean.ShoppingCart;
import jtech.shopzone.model.dal.bean.ShoppingCartId;
import jtech.shopzone.model.dal.bean.Userinfo;
import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CartDao;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;
import jtech.shopzone.model.entity.StockStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mahrous
 */
public class CartDaoImpl implements CartDao {
    private Session instanceSession = MySessionFactory.getMySessionFactory().getSession();


    @Override
    public Status addProduct(int userId, int productId) {
        Status status = null;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        Userinfo userinfo = session.load(Userinfo.class, userId);
        ProductsInfo productsInfo = session.load(ProductsInfo.class, productId);

        ShoppingCartId shoppingCartId = new ShoppingCartId();
        shoppingCartId.setProductId(productsInfo.getProductId());
        shoppingCartId.setUserId(userinfo.getUserId());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);
        shoppingCart.setUserinfo(userinfo);
        shoppingCart.setProductsInfo(productsInfo);
        shoppingCart.setQuantity(1);

        session.beginTransaction();
        session.persist(shoppingCart);

        try {
            session.getTransaction().commit();
            status = Status.OK;
        } catch (Exception e) {
            session.getTransaction().rollback();
            status = Status.NOTOK;
        }

        session.close();

        return status;
    }

    @Override
    public Status deleteProduct(int userId, int productId) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        Userinfo userinfo = session.load(Userinfo.class, userId);
        ProductsInfo productsInfo = session.load(ProductsInfo.class, productId);
        Query query = session.createQuery("FROM ShoppingCart s WHERE s.userinfo = :user and s.productsInfo = :product").setParameter("user", userinfo).setParameter("product", productsInfo);

        ShoppingCart shoppingCart = (ShoppingCart) query.list().get(0);

        try {
            session.beginTransaction();
            session.remove(shoppingCart);
            session.getTransaction().commit();
            status = Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            status = Status.NOTOK;
        }
        session.close();
        return status;
    }

    @Override
    public ArrayList<ShoppingCart> getUserProducts(int userId) {

        ArrayList<ShoppingCart> cartEntities = new ArrayList<>();
        Userinfo userinfo = instanceSession.load(Userinfo.class, userId);
        Query query = instanceSession.createQuery("FROM ShoppingCart s WHERE s.userinfo = :user")
                .setParameter("user", userinfo);
        try {
            List result = query.list();
            for (Object res : result) {
                cartEntities.add((ShoppingCart) res);
            }
        } catch (Exception e) {
            cartEntities = null;
            e.printStackTrace();
        }
        return cartEntities;
    }

    @Override
    public Status updateProductQuantities(int userId, int productId, int quantities) {
        Status status;
        String query = "UPDATE SHOPPING_CART SET QUANTITY=" + quantities + " WHERE USER_ID=" + userId + " AND PRODUCT_ID=" + productId;
        status = execUpdate(query);
        return status;
    }

    @Override
    public Status checkProductExistance(int userId, int productId) {
        // Return object
        Status status = Status.NOTOK;

        // Build product check select statement
        String productQuery = "select * from  shopping_cart where USER_ID =" + userId + " and PRODUCT_ID =" + productId;

        // get new statement from the connection
        try (Statement statement = DbConnection.getStatement()) {

            // execute select statement and get results
            ResultSet resultSet = statement.executeQuery(productQuery);

            // if result exists then return
            if (resultSet.next()) {
                status = Status.OK;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.ERROR;
        }
        return status;

    }

    @Override
    public int userItemCount(int userId) {
        int count;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        Userinfo userinfo = session.load(Userinfo.class, userId);
        Query query = session.createQuery("SELECT SUM(quantity) FROM ShoppingCart s WHERE s.userinfo = :user").setParameter("user", userinfo);

        try {
            List result = query.list();
            Long productsCount = (Long) result.get(0);
            count = productsCount.intValue();
        } catch (Exception e) {
            count = -1;
            e.printStackTrace();
        }
        session.close();
        return count;
    }

    @Override
    public Status restCart(int userId) {
        Status status;
        String query = "DELETE FROM SHOPPING_CART WHERE USER_ID=" + userId;
        status = execUpdate(query);
        return status;
    }

    @Override
    public int getQuantity(int userId, int productId) {
        int quantity = -1; // if this is returned then error happened
        try (Statement statement = DbConnection.getStatement()) {
            String query = "SELECT QUANTITY FROM SHOPPING_CART WHERE PRODUCT_ID=" + productId + " and USER_ID=" + userId;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                quantity = resultSet.getInt("QUANTITY");
            } else {
                quantity = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    @Override
    public StockStatus getStockStatus(int productId, int quantity) {
        StockStatus stockStatus = null;
        // get product using product dao
        ProductDao productDao = new ProductDaoImpl();
        ProductsInfoEntity productsInfoEntity = productDao.getProductInfo(productId);

        if (productsInfoEntity.getQuantity() >= quantity) {
            stockStatus = StockStatus.IN_STOCK;
        } else {
            stockStatus = StockStatus.OUT_OF_STOCK;
        }

        return stockStatus;

    }

    private Status execUpdate(String query) {
        Status status;
        try (Statement statement = DbConnection.getStatement()) {
            int rowCount = statement.executeUpdate(query);
            if (rowCount > 0) {
                status = Status.OK;
            } else {
                status = Status.NOTOK;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.ERROR;
        }
        return status;
    }
}
