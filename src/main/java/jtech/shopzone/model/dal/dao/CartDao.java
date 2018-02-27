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
public interface CartDao {

    DataBaseStatusEnum AddProduct(Product product);

    DataBaseStatusEnum deleteProduct(int productId);

    ArrayList<Product> getUserProducts(int userId);

    DataBaseStatusEnum updateProductQuantities(int quantities);

    int ckeckProductExistance(int productId);

    int userItemCount(int userId);

}
