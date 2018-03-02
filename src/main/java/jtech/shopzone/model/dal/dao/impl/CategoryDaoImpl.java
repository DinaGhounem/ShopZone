/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CategoryDao;
import jtech.shopzone.model.entity.ProductCategoryEntity;

/**
 *
 * @author Hanaa
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public ArrayList<ProductCategoryEntity> getCategories() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ProductCategoryEntity> categoryEntitys = new ArrayList<>();
        try {

            preparedStatement = DbConnection.getPreparedStatement("select * from PRODUCTS_CATEGORY");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProductCategoryEntity category = new ProductCategoryEntity();
                category.setCategoryId(resultSet.getInt("CATEGORY_ID"));
                category.setCategoryName(resultSet.getString("CATEGORY_NAME"));
                categoryEntitys.add(category);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                DbConnection.closeStatementAndResultSet(preparedStatement, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categoryEntitys;
    }

    @Override
    public int getCategoryByName(String categoryName) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = DbConnection.getPreparedStatement("select CATEGORY_ID from PRODUCTS_CATEGORY where CATEGORY_NAME='" + categoryName + "'");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt("CATEGORY_ID");

            } else {
                return -1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(preparedStatement, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getCategortById(int categoryId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = DbConnection.getPreparedStatement("select CATEGORY_NAME from PRODUCTS_CATEGORY where CATEGORY_ID=" + categoryId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getString("CATEGORY_NAME");

            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                DbConnection.closeStatementAndResultSet(preparedStatement, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public Status addCategory(ProductCategoryEntity category) {
        if (category.getCategoryName() != null) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            int categoryId = 0;
            try {
                preparedStatement = DbConnection.getPreparedStatement("select nvl(max(Category_id),0) from PRODUCTS_CATEGORY");
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    categoryId = resultSet.getInt(1) + 1;

                } else {
                    return Status.NOTOK;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                return Status.ERROR;

            }
            try {
                preparedStatement = DbConnection.getPreparedStatement("insert into PRODUCTS_CATEGORY values(?,?)");
                preparedStatement.setInt(1, categoryId);
                preparedStatement.setString(2, category.getCategoryName());

                if (preparedStatement.executeUpdate() > 0) {
                    return Status.OK;
                } else {
                    return Status.NOTOK;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                return Status.ERROR;
            } finally {
                try {

                    DbConnection.closeStatementAndResultSet(preparedStatement, resultSet);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return Status.NOTOK;
    }
    // just for testing :))

    /*public static void main(String[] args) {
        ProductCategoryEntity category = new ProductCategoryEntity();
        category.setCategoryName("drinks");
        CategoryDaoImpl cdi = new CategoryDaoImpl();
        ArrayList<ProductCategoryEntity>arr=cdi.getCategories();
        for (ProductCategoryEntity productCategoryEntity : arr) {
             System.out.println(productCategoryEntity.getCategoryName());
        }
       

    }*/
}
