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
import jtech.shopzone.model.entity.ProductsInfoEntity;
import jtech.shopzone.model.entity.StockStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahrous
 */
public class CartDaoImpl implements CartDao {
    private Session instanceSession = MySessionFactory.getMySessionFactory().getSession();
    private Session cartQuantityNotificationSession = MySessionFactory.getMySessionFactory().getSession();

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
        try {
            Userinfo userinfo = session.load(Userinfo.class, userId);
            ProductsInfo productsInfo = session.load(ProductsInfo.class, productId);

            Query query = session.createQuery("FROM ShoppingCart s WHERE s.userinfo = :user and s.productsInfo = :product").setParameter("user", userinfo).setParameter("product", productsInfo);
            if (productsInfo != null && userinfo != null) {
                ShoppingCart shoppingCart = (ShoppingCart) query.list().get(0);
                session.beginTransaction();
                session.remove(shoppingCart);
                session.getTransaction().commit();
                status = Status.OK;
            } else {
                status = Status.NOTOK;
            }


        } catch (Exception e) {
            e.printStackTrace();
            status = Status.ERROR;
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

        Session session = MySessionFactory.getMySessionFactory().getSession();

        try {
            session.beginTransaction();
            ProductsInfo productsInfo = session.load(ProductsInfo.class, productId);
            Userinfo userinfo = session.load(Userinfo.class, userId);
            if (productsInfo != null && userinfo != null) {
                Query query = session
                        .createQuery("FROM ShoppingCart WHERE userinfo= :user and productsInfo= :product")
                        .setParameter("user", userinfo)
                        .setParameter("product", productsInfo);
                ShoppingCart shoppingCart = (ShoppingCart) query.list().get(0);
                shoppingCart.setQuantity(quantities);
                session.persist(shoppingCart);
                session.getTransaction().commit();
                status = Status.OK;
            } else {
                status = Status.NOTOK;
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            status = Status.ERROR;
        }

        session.close();
        return status;
    }

    @Override
    public Status checkProductExistence(int userId, int productId) {

        Status status;

        try {
            ShoppingCartId shoppingCartId = new ShoppingCartId(userId, productId);
            ShoppingCart shoppingCart = instanceSession.get(ShoppingCart.class, shoppingCartId);
            if (shoppingCart != null) {
                status = Status.OK;
            } else {
                status = Status.NOTOK;
            }

        } catch (Exception e) {
            e.printStackTrace();
            status = Status.ERROR;
        }
        return status;

    }

    @Override
    public int userItemCount(int userId) {
        int count;
        Userinfo userinfo = cartQuantityNotificationSession.load(Userinfo.class, userId);
        Query query = cartQuantityNotificationSession.createQuery("SELECT SUM(quantity) FROM ShoppingCart s WHERE s.userinfo = :user").setParameter("user", userinfo);

        try {
            List result = query.list();
            Long productsCount = (Long) result.get(0);
            count = productsCount.intValue();
        } catch (Exception e) {
            count = -1;
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int getQuantity(int userId, int productId) {
        int quantity;
        try {
            Userinfo userinfo = instanceSession.load(Userinfo.class, userId);
            ProductsInfo productsInfo = instanceSession.load(ProductsInfo.class, productId);

            if (userinfo != null && productsInfo != null) {
                Query query = instanceSession.createQuery("SELECT SUM(s.quantity) FROM ShoppingCart s WHERE s.userinfo= :user and s.productsInfo = :product ")
                        .setParameter("user", userinfo)
                        .setParameter("product", productsInfo);
                Long dbQuantity = (Long) query.list().get(0);
                quantity = dbQuantity.intValue();
            } else {
                quantity = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            quantity = -1;
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

}
