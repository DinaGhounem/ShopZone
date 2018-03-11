package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.TransactionReport;
import jtech.shopzone.model.entity.UserProductsEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * @Author Muhammed Mahrous
 */
public interface TransactionsDao {

    ArrayList<UserProductsEntity> getUserHistory(int userId);

    ArrayList<TransactionReport> checkOut(int userId);

    Status addToHistory(int userId, CartEntity cartEntity);

}
