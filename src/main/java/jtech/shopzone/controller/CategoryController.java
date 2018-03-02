/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.controller;

import java.util.ArrayList;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.ProductCategoryEntity;

/**
 *
 * @author Hanaa
 */
public interface CategoryController {

    Status addCategory(ProductCategoryEntity category);

     ArrayList<ProductCategoryEntity> getCategories();

    int getCategoryByName(String categoryName);

    String getCategortById(int categoryId);

}
