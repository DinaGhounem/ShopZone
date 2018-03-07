package jtech.shopzone.controller;

import jtech.shopzone.model.entity.TransactionReport;
import jtech.shopzone.model.entity.UserProductsEntity;

import java.util.ArrayList;

public interface TransactionsController {

    ArrayList<UserProductsEntity> getUserHistory(int userId);

    ArrayList<TransactionReport> checkOut(int userId);
}
