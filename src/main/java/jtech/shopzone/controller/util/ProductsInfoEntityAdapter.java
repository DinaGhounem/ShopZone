package jtech.shopzone.controller.util;

import jtech.shopzone.controller.dal.bean.ProductsInfo;
import jtech.shopzone.model.entity.ProductsInfoEntity;

public class ProductsInfoEntityAdapter {

    public static ProductsInfoEntity toProductsInfo(ProductsInfo productsInfo) {
        ProductsInfoEntity productsInfoEntity = new ProductsInfoEntity();
        productsInfoEntity.setDeletedFlg(productsInfo.getDeletedFlg());
        productsInfoEntity.setDescription(productsInfo.getDescription());
        productsInfoEntity.setImg(productsInfo.getImg());
        productsInfoEntity.setCategoryId(productsInfo.getProductsCategory().getCategoryId());
        productsInfoEntity.setQuantity(productsInfo.getQuantity());
        productsInfoEntity.setPrice(productsInfo.getPrice());
        productsInfoEntity.setProductName(productsInfo.getProductName());
        productsInfoEntity.setProductId(productsInfo.getProductId());
        return productsInfoEntity;
    }
}
