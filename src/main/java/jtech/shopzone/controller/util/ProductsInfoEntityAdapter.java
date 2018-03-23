package jtech.shopzone.controller.util;

import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.bean.ProductsInfo;
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
    
    public static ProductsInfo fromProductsInfoEntity(ProductsInfoEntity productsInfoEntity){
        ProductsInfo productsInfo = new ProductsInfo();
        
        ProductsCategory cat = new ProductsCategory();
        cat.setCategoryId(productsInfoEntity.getCategoryId());
        
        productsInfo.setDeletedFlg(productsInfoEntity.getDeletedFlg());
        productsInfo.setDescription(productsInfoEntity.getDescription());
        productsInfo.setImg(productsInfoEntity.getImg());
        productsInfo.setProductsCategory(cat);
        productsInfo.setQuantity(productsInfoEntity.getQuantity());
        productsInfo.setPrice((long)productsInfoEntity.getPrice());
        productsInfo.setProductName(productsInfoEntity.getProductName());
        //productsInfo.setProductId(productsInfoEntity.getProductId());
        return productsInfo;
    }
}
