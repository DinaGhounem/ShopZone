/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.DBStatus;
import java.util.ArrayList;
import jtech.shopzone.model.entity.ProductsInfoEntity;

/**
 *
 * @author Hanaa
 */
public interface CartDao {

    public DBStatus AddProduct(int userId, ProductsInfoEntity product);

    public DBStatus deleteProduct(int userId, int productId);

    public ArrayList<ProductsInfoEntity> getUserProducts(int userId);

    public DBStatus updateProductQuantities(int userId, int productId, int quantities);

    public int ckeckProductExistance(int userId,int productId);

    public int userItemCount(int userId);

}
