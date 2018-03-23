package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.bean.*;
import jtech.shopzone.controller.util.ShoppingCartToCartEntityAdaptor;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CartDao;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.dal.dao.TransactionsDao;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Muhammed Mahrous
 */
public class TransactionDaoImpl implements TransactionsDao {
    private Session instanceSession = MySessionFactory.getMySessionFactory().getSession();

    @Override
    public ArrayList<UserProducts> getUserHistory(int userId) {
        ArrayList<UserProducts> transactionsList = null;
        try {
            Userinfo userinfo = instanceSession.load(Userinfo.class, userId);
            if (userinfo != null) {
                Query query = instanceSession.createQuery("FROM UserProducts u WHERE u.userinfo = :user")
                        .setParameter("user", userinfo);
                List<UserProducts> userProducts = query.list();
                transactionsList = new ArrayList<>(userProducts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Return arraylist
        return transactionsList;

    }

    @Override
    public ArrayList<TransactionReport> checkOut(int userId) {
        ArrayList<TransactionReport> transactionReports = new ArrayList<>();

        ProductDao productDao = new ProductDaoImpl();
        UserDao userDao = new UserDaoImpl();

        // Get cart content for that user
        CartDao cartDao = new CartDaoImpl();
        ArrayList<CartEntity> cartEntities = null;

        ArrayList<ShoppingCart> shoppingCarts = cartDao.getUserProducts(userId);
        if (shoppingCarts != null) {
            cartEntities = new ArrayList<>();
            for (ShoppingCart shoppingCart : shoppingCarts) {
                StockStatus stockStatus = cartDao.getStockStatus(shoppingCart.getProductsInfo().getProductId(), shoppingCart.getQuantity());
                cartEntities.add(ShoppingCartToCartEntityAdaptor.toCartEntity(shoppingCart, stockStatus));
            }
        }


        // for each cart entry try to execute transaction
        // and add its full report into arraylist
        for (CartEntity cartEntity : cartEntities) {
            TransactionReport transactionReport = new TransactionReport();
            transactionReport.setCartEntity(cartEntity);
            // check if stock has enough quantity
            if (cartEntity.getStockStatus().equals(StockStatus.OUT_OF_STOCK)) {
                // Since stock is not enough then this transaction will not be
                // completed
                transactionReport.setStatus(Status.NOTOK);
                transactionReport.setComment("Out Of Stock");
            } else if (cartEntity.getStockStatus().equals(StockStatus.IN_STOCK)) {
                Userinfo userinfo = userDao.getUserInfo(userId);
                // Check if user has enough money
                double creditLimit = userinfo.getCreditLimit();
                double totalPrice = cartEntity.getQuantity() * cartEntity.getProductsInfoEntity().getPrice();
                if (creditLimit < totalPrice) {
                    transactionReport.setComment("Not Enough Credit");
                    transactionReport.setStatus(Status.NOTOK);
                } else {
                    // All set to execute the transaction

                    // Cut price from user's credit limit
                    userDao.updateCreditLimit(userId, totalPrice);

                    // Cut product quantity from stock
                    int newQuantity = cartEntity.getProductsInfoEntity().getQuantity() - cartEntity.getQuantity();
                    productDao.updateProductQuantities(cartEntity.getProductsInfoEntity().getProductId(), newQuantity);

                    // remove entry from cart
                    cartDao.deleteProduct(userId, cartEntity.getProductsInfoEntity().getProductId());

                    // Add to history
                    addToHistory(userId, cartEntity);

                    // Write report status
                    transactionReport.setStatus(Status.OK);
                }


            }
            transactionReports.add(transactionReport);
        }


        return transactionReports;
    }

    @Override
    public Status addToHistory(int userId, CartEntity cartEntity) {
        Status status = null;
        ProductsInfoEntity productsInfoEntity = cartEntity.getProductsInfoEntity();
        int quantity = cartEntity.getQuantity();
        double totalPrice = quantity * productsInfoEntity.getPrice();
        Session session = MySessionFactory.getMySessionFactory().getSession();

        try {
            instanceSession.beginTransaction();
            ProductsInfo productsInfo = instanceSession.load(ProductsInfo.class, cartEntity.getProductsInfoEntity().getProductId());
            Userinfo userinfo = instanceSession.load(Userinfo.class, userId);
            if (productsInfo != null && userinfo != null) {
                UserProducts userProducts = new UserProducts();
                userProducts.setId(new UserProductsId(userId,productsInfo.getProductId()));
                userProducts.setOrderDate(new Date());
                userProducts.setPrice((long) totalPrice);
                userProducts.setQuantity(cartEntity.getQuantity());
                userProducts.setProductsInfo(productsInfo);
                userProducts.setUserinfo(userinfo);
                instanceSession.persist(userProducts);
                status = Status.OK;
                instanceSession.getTransaction().commit();
            } else {
                status = Status.NOTOK;
                instanceSession.getTransaction().rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
            instanceSession.getTransaction().rollback();
            status = Status.ERROR;
        }
        return status;
    }

}
