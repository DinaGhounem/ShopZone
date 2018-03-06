package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.dal.dao.TransactionsDao;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;
import jtech.shopzone.model.entity.StockStatus;
import jtech.shopzone.model.entity.UserProductsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransactionDaoImpl implements TransactionsDao {
    @Override
    public ArrayList<UserProductsEntity> getUserHistory(int userId) {

        ArrayList<UserProductsEntity> transactionsList = new ArrayList<>();

        try (Statement statement = DbConnection.getStatement()){
            String query = "select *  from  USER_PRODUCTS  where USER_ID =" + userId;
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                UserProductsEntity userProductsEntity = new UserProductsEntity();
                userProductsEntity.setUserId(userId);
                userProductsEntity.setProductId(resultSet.getInt("PRODUCT_ID"));
                userProductsEntity.setQuantity(resultSet.getInt("QUANTITY"));
                userProductsEntity.setDate(resultSet.getDate("ORDER_DATE"));
                userProductsEntity.setPrice(resultSet.getInt("PRICE"));
                transactionsList.add(userProductsEntity);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return arraylist
        return transactionsList;

    }

    public static void main(String[] args) {
        TransactionDaoImpl transactionDao = new TransactionDaoImpl();
        System.out.println(transactionDao.getUserHistory(1));
    }
}
