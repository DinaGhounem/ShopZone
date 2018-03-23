/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import java.util.ArrayList;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.entity.ProductCategoryEntity;

/**
 * @author Hanaa
 */
public interface CategoryDao {

    Status addCategory(ProductCategoryEntity category);

    ArrayList<ProductsCategory> getCategories();

    int getCategoryByName(String categoryName);

    String getCategortById(int categoryId);

}
