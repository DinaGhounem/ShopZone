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
public interface ProductDao {

    public ArrayList<ProductsInfoEntity> getProducts();

    public ArrayList<ProductsInfoEntity> getProducts(int categoryId);

    public ArrayList<ProductsInfoEntity> getProducts(Double price);

    public DBStatus AddProduct(ProductsInfoEntity product);

    public DBStatus deleteProduct(int productId);

    public DBStatus updateProduct(ProductsInfoEntity product);

    public int checkProductQuantities(int productId);

    public DBStatus updateProductQuantities(int productId, int quantities);

}
