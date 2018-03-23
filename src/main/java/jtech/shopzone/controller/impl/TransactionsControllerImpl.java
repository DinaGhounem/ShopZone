package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.TransactionsController;
import jtech.shopzone.controller.util.UserProductsEntityAdaptor;
import jtech.shopzone.model.dal.bean.ProductsInfo;
import jtech.shopzone.model.dal.bean.UserProducts;
import jtech.shopzone.model.dal.dao.TransactionsDao;
import jtech.shopzone.model.dal.dao.impl.TransactionDaoImpl;
import jtech.shopzone.model.entity.TransactionReport;
import jtech.shopzone.model.entity.UserProductsEntity;

import java.util.ArrayList;

/**
 * @Author Muhammed Mahrous
 */
public class TransactionsControllerImpl implements TransactionsController {
    private TransactionsDao transactionsDao;

    private TransactionsControllerImpl() {
        this.transactionsDao = new TransactionDaoImpl();
    }

    public static TransactionsControllerImpl newInstance() {
        return new TransactionsControllerImpl();
    }

    @Override
    public ArrayList<UserProductsEntity> getUserHistory(int userId) {
        ArrayList<UserProducts> userHistory = transactionsDao.getUserHistory(userId);
        ArrayList<UserProductsEntity> userProductsEntities = new ArrayList<>();
        for (UserProducts userProducts : userHistory) {
            UserProductsEntity userProductsEntity = UserProductsEntityAdaptor.toUserProductsEntity(userProducts);
            userProductsEntities.add(userProductsEntity);
        }

        return userProductsEntities;

    }

    @Override
    public ArrayList<TransactionReport> checkOut(int userId) {
        return transactionsDao.checkOut(userId);
    }
}
