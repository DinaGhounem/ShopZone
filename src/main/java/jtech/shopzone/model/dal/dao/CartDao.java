/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.Status;

import java.util.ArrayList;

import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;

/**
 * @author Hanaa
 */
public interface CartDao {

    Status AddProduct(int userId, ProductsInfoEntity product);

    Status deleteProduct(int userId, int productId);

    ArrayList<CartEntity> getUserProducts(int userId);

    Status updateProductQuantities(int userId, int productId, int quantities);

    Status checkProductExistance(int userId, int productId);

    int userItemCount(int userId);

}
