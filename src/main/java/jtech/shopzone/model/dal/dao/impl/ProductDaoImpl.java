package jtech.shopzone.model.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtech.shopzone.model.dal.DbConnection;

public class ProductDaoImpl implements ProductDao {

    Connection connection = null;

    public ProductDaoImpl() {
        connection = DbConnection.getConnection();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts() {
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        String query = "SELECT * from PRODUCTS_INFO";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductsInfoEntity product = new ProductsInfoEntity();
                product.setProductId(resultSet.getInt("PRODUCT_ID"));
                product.setProductName(resultSet.getString("PRODUCT_NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setDescription(resultSet.getString("DESCRIPTION"));
                product.setCategoryId(resultSet.getInt("CATEGORY_ID"));
                product.setImg(resultSet.getString("IMG"));
                products.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(int categoryId) {
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        String query = "SELECT * from PRODUCTS_INFO where CATEGORY_ID='" + categoryId + "'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductsInfoEntity product = new ProductsInfoEntity();
                product.setProductId(resultSet.getInt("PRODUCT_ID"));
                product.setProductName(resultSet.getString("PRODUCT_NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setDescription(resultSet.getString("DESCRIPTION"));
                product.setCategoryId(resultSet.getInt("CATEGORY_ID"));
                product.setImg(resultSet.getString("IMG"));
                products.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(double minPrice, double maxPrice) {
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        String query = "SELECT * from PRODUCTS_INFO where PRICE between " + minPrice + " and " + maxPrice;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductsInfoEntity product = new ProductsInfoEntity();
                product.setProductId(resultSet.getInt("PRODUCT_ID"));
                product.setProductName(resultSet.getString("PRODUCT_NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));
                product.setQuantity(resultSet.getInt("QUANTITY"));
                product.setDescription(resultSet.getString("DESCRIPTION"));
                product.setCategoryId(resultSet.getInt("CATEGORY_ID"));
                product.setImg(resultSet.getString("IMG"));
                products.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }

    @Override
    public Status AddProduct(ProductsInfoEntity product) {
        return null;
    }

    @Override
    public Status deleteProduct(int productId) {
        return null;
    }

    @Override
    public Status updateProduct(ProductsInfoEntity product) {
        return null;
    }

    @Override
    public int checkProductQuantities(int productId) {
        return 0;
    }

    @Override
    public Status updateProductQuantities(int productId, int quantities) {
        return null;
    }
}
