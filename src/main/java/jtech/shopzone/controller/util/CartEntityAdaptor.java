package jtech.shopzone.controller.util;

import jtech.shopzone.model.dal.bean.ShoppingCart;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;
import jtech.shopzone.model.entity.StockStatus;

public class CartEntityAdaptor {

    public static CartEntity toCartEntity(ShoppingCart shoppingCart, StockStatus stockStatus) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setQuantity(shoppingCart.getQuantity());
        ProductsInfoEntity productsInfoEntity = ProductsInfoEntityAdapter.toProductsInfo(shoppingCart.getProductsInfo());
        cartEntity.setProductsInfoEntity(productsInfoEntity);
        cartEntity.setStockStatus(stockStatus);
        return cartEntity;
    }

}
