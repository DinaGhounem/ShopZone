package jtech.shopzone.controller.util;

import jtech.shopzone.model.dal.bean.UserProducts;
import jtech.shopzone.model.entity.UserProductsEntity;

public class UserProductsEntityAdaptor {

    public static UserProductsEntity toUserProductsEntity(UserProducts userProducts) {
        UserProductsEntity userProductsEntity = new UserProductsEntity();
        userProductsEntity.setProductName(userProducts.getProductsInfo().getProductName());
        userProductsEntity.setPrice(userProducts.getPrice());
        userProductsEntity.setDate(userProducts.getOrderDate());
        userProductsEntity.setProductId(userProducts.getId().getProductId());
        userProductsEntity.setUserId(userProducts.getId().getUserId());
        userProductsEntity.setQuantity(userProducts.getQuantity());
        return userProductsEntity;
    }
}
