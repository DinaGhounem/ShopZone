package jtech.shopzone.controller.util;

import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.bean.ProductsInfo;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

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

    public static ArrayList<ProductsInfoEntity> toProductsInfoEntities(ArrayList<ProductsInfo> productsInfos) {
        ArrayList<ProductsInfoEntity> productsInfoEntities = new ArrayList<>();
        for (ProductsInfo productsInfo : productsInfos) {
            productsInfoEntities.add(toProductsInfo(productsInfo));
        }
        return productsInfoEntities;
    }

    public static ProductsInfo fromProductsInfoEntity(ProductsInfoEntity productsInfoEntity) {
        ProductsInfo productsInfo = new ProductsInfo();
        productsInfo.setDeletedFlg(productsInfoEntity.getDeletedFlg());
        productsInfo.setDescription(productsInfoEntity.getDescription());
        productsInfo.setImg(productsInfoEntity.getImg());
        productsInfo.setQuantity(productsInfoEntity.getQuantity());
        productsInfo.setPrice((long) productsInfoEntity.getPrice());
        productsInfo.setProductName(productsInfoEntity.getProductName());
        return productsInfo;
    }

    public static ProductsInfo updateProductsInfo(ProductsInfo productsInfo, ProductsInfoEntity productsInfoEntity) {
        productsInfo.setDeletedFlg(productsInfoEntity.getDeletedFlg());
        productsInfo.setDescription(productsInfoEntity.getDescription());
        productsInfo.setImg(productsInfoEntity.getImg());
        productsInfo.setQuantity(productsInfoEntity.getQuantity());
        productsInfo.setPrice((long) productsInfoEntity.getPrice());
        productsInfo.setProductName(productsInfoEntity.getProductName());
        return productsInfo;
    }
}
