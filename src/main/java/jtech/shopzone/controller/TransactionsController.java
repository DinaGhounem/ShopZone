package jtech.shopzone.controller;

import jtech.shopzone.model.entity.UserProductsEntity;

public interface TransactionsController {

    UserProductsEntity getUserHistory(int userId);

}
