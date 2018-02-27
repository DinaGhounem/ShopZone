/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.DataBaseStatusEnum;
import java.util.ArrayList;
import jtech.shopzone.model.entity.ProductsInfoEntity;

/**
 *
 * @author Hanaa
 */
public interface CartDao {

    public DataBaseStatusEnum AddProduct(int userId,ProductsInfoEntity product);

    public DataBaseStatusEnum deleteProduct(int userId,int productId);

    public ArrayList<ProductsInfoEntity> getUserProducts(int userId);

    public DataBaseStatusEnum updateProductQuantities(int userId,int productId,int quantities);

    public int ckeckProductExistance(int userId,int productId);

    public int userItemCount(int userId);

}
