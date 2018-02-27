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
public interface ProductDao {

    public ArrayList<ProductsInfoEntity> getProducts();

    public ArrayList<ProductsInfoEntity> getProducts(int categoryId);

    public ArrayList<ProductsInfoEntity> getProducts(Double price);

    public DataBaseStatusEnum AddProduct(ProductsInfoEntity product);

    public DataBaseStatusEnum deleteProduct(int productId);

    public DataBaseStatusEnum updateProduct(ProductsInfoEntity product);

    public int checkProductQuantities(int productId);

    public DataBaseStatusEnum updateProductQuantities(int productId,int quantities);

}
