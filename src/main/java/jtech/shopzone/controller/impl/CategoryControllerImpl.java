/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.controller.impl;

import java.util.ArrayList;

import jtech.shopzone.controller.CategoryController;
import jtech.shopzone.controller.util.ProductCategoryEntityAdaptor;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.dao.CategoryDao;
import jtech.shopzone.model.dal.dao.impl.CategoryDaoImpl;
import jtech.shopzone.model.entity.ProductCategoryEntity;

/**
 * @author Hanaa
 */
public class CategoryControllerImpl implements CategoryController {

    private CategoryDao categoryDao;

    private CategoryControllerImpl() {
        categoryDao = new CategoryDaoImpl();
    }

    public static CategoryControllerImpl newInstance() {
        return new CategoryControllerImpl();
    }

    @Override
    public Status addCategory(ProductCategoryEntity category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public ArrayList<ProductCategoryEntity> getCategories() {
        ArrayList<ProductsCategory> productsCategories = categoryDao.getCategories();
        ArrayList<ProductCategoryEntity> productCategoryEntities = null;
        if (productsCategories != null) {
            productCategoryEntities = new ArrayList<>();
            for (ProductsCategory productsCategory : productsCategories) {
                productCategoryEntities.add(ProductCategoryEntityAdaptor.toProductCategoryEntity(productsCategory));
            }
        }

        return productCategoryEntities;
    }

    @Override
    public int getCategoryByName(String categoryName) {
        return categoryDao.getCategoryByName(categoryName);
    }

    @Override
    public String getCategortById(int categoryId) {
        return categoryDao.getCategortById(categoryId);
    }

}
