package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.entity.UserProductsEntity;

import java.util.ArrayList;

public interface TransactionsDao {

    ArrayList<UserProductsEntity> getUserHistory(int userId);


}
