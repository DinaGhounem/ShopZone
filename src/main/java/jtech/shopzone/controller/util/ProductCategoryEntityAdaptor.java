package jtech.shopzone.controller.util;

import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.entity.ProductCategoryEntity;

public class ProductCategoryEntityAdaptor {
    public static ProductCategoryEntity toProductCategoryEntity(ProductsCategory productsCategory) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        productCategoryEntity.setCategoryName(productsCategory.getCategoryName());
        productCategoryEntity.setCategoryId(productsCategory.getCategoryId());
        return productCategoryEntity;
    }
}
