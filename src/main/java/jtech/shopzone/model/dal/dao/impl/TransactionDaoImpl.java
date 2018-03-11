package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.controller.CartController;
import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CartDao;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.dal.dao.TransactionsDao;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TransactionDaoImpl implements TransactionsDao {
    @Override
    public ArrayList<UserProductsEntity> getUserHistory(int userId) {

        ArrayList<UserProductsEntity> transactionsList = new ArrayList<>();

        try (Statement statement = DbConnection.getStatement()) {
            String query = "select *  from  USER_PRODUCTS  userProduct , PRODUCTS_INFO  product where userProduct.PRODUCT_ID=product.PRODUCT_ID and USER_ID ="+userId;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                UserProductsEntity userProductsEntity = new UserProductsEntity();
                userProductsEntity.setUserId(userId);
                userProductsEntity.setProductId(resultSet.getInt("PRODUCT_ID"));
                userProductsEntity.setQuantity(resultSet.getInt("QUANTITY"));
                userProductsEntity.setDate(resultSet.getDate("ORDER_DATE"));
                userProductsEntity.setPrice(resultSet.getInt("PRICE"));
                userProductsEntity.setProductName(resultSet.getString("PRODUCT_NAME"));
                transactionsList.add(userProductsEntity);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return arraylist
        return transactionsList;

    }

    @Override
    public ArrayList<TransactionReport> checkOut(int userId) {
        ProductDao productDao = new ProductDaoImpl();
        UserDao userDao = new UserDaoImpl();
        ArrayList<TransactionReport> transactionReports = new ArrayList<>();

        // Get cart content for that user
        CartDao cartDao = new CartDaoImpl();
        ArrayList<CartEntity> cartEntities = cartDao.getUserProducts(userId);

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
                UserInfoEntity userInfoEntity = userDao.getUserInfo(userId);
                // Check if user has enough money
                double creditLimit = userInfoEntity.getCreditLimit();
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
        Date date = new Date();
        java.sql.Date sqlDateNow = new java.sql.Date(date.getTime());

        String query = "INSERT INTO USER_PRODUCTS VALUES(?,?,?,?,?)";

        try (PreparedStatement statement = DbConnection.getPreparedStatement(query)) {

            statement.setInt(1, userId);
            statement.setInt(2, productsInfoEntity.getProductId());
            statement.setInt(3, quantity);
            statement.setDate(4, sqlDateNow);
            statement.setDouble(5, totalPrice);

            int rowCount = statement.executeUpdate();
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
