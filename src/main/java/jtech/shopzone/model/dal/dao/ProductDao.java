/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.DataBaseStatusEnum;
import java.util.ArrayList;

/**
 *
 * @author Hanaa
 */
public interface ProductDao {

    ArrayList<Product> getProducts();

    ArrayList<Product> getProducts(int categoryId);

    ArrayList<Product> getProducts(Double price);

    DataBaseStatusEnum AddProduct(Product product);

    DataBaseStatusEnum deleteProduct(int productId);

    DataBaseStatusEnum updateProduct(Product product);

    int checkProductQuantities(int productId);

    DataBaseStatusEnum updateProductQuantities(int quantities);

}
