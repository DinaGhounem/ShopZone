package jtech.shopzone.model.dal.dao.impl;

import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.CartDao;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.CartEntity;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.sql.*;
import java.util.ArrayList;

public class CartDaoImpl implements CartDao {
    @Override
    public Status addProduct(int userId, int productId) {
        Status status;
        String query = "INSERT INTO SHOPPING_CART VALUES(" + userId + "," + productId + ",1)";
        status = execUpdate(query);
        return status;
    }

    @Override
    public Status deleteProduct(int userId, int productId) {
        Status status;
        String query = "DELETE FROM SHOPPING_CART WHERE USER_ID=" + userId + " and PRODUCT_ID=" + productId;
        status = execUpdate(query);
        return status;
    }

    @Override
    public ArrayList<CartEntity> getUserProducts(int userId) {

        // Init empty arraylist to fill products info in it
        ArrayList<CartEntity> productsWithQuantity = new ArrayList<>();

        try (Statement statement = DbConnection.getStatement()){
            // build Outer SQL string to get cart entities
            String outerQuery = "select PRODUCT_ID , QUANTITY  from  shopping_cart where USER_ID =" + userId;

            // get result set of cart entities
            ResultSet resultSet = statement.executeQuery(outerQuery);

            // loop on cart entitites
            while (resultSet.next()) {
                // get quantity of current product in cart
                int productQuantity = resultSet.getInt("QUANTITY");

                // get product id of current product in cart
                int productID = resultSet.getInt("PRODUCT_ID");

                // User product dao to get product info
                ProductDao productDao = new ProductDaoImpl();
                ProductsInfoEntity product = productDao.getProductInfo(productID);

                // add the product to products with quantity list
                CartEntity cartEntity = new CartEntity(productQuantity, product);
                productsWithQuantity.add(cartEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return arraylist
        return productsWithQuantity;
    }

    @Override
    public Status updateProductQuantities(int userId, int productId, int quantities) {
        Status status;
        String query = "UPDATE SHOPPING_CART SET QUANTITY="+quantities+" WHERE USER_ID="+userId+" AND PRODUCT_ID="+productId;
        status = execUpdate(query);
        return status;
    }

    @Override
    public Status checkProductExistance(int userId, int productId) {
        // Return object
        Status status = Status.NOTOK;

        // Build product check select statement
        String productQuery = "select * from  shopping_cart where USER_ID =" + userId + " and PRODUCT_ID =" + productId;

        // get new statement from the connection
        try (Statement statement = DbConnection.getStatement()) {

            // execute select statement and get results
            ResultSet resultSet = statement.executeQuery(productQuery);

            // if result exists then return
            if (resultSet.next()) {
                status = Status.OK;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.ERROR;
        }
        return status;

    }

    @Override
    public int userItemCount(int userId) {
        int count;

        try (Statement statement = DbConnection.getStatement()){
            String sql = "SELECT SUM(QUANTITY) as itemCount FROM SHOPPING_CART WHERE USER_ID ="+userId;
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next())
            {
                count = resultSet.getInt("itemCount");
            }
            else
            {
                count = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            count = -1;
        }

        return count;
    }

    @Override
    public Status restCart(int userId) {
        Status status;
        String query = "DELETE FROM SHOPPING_CART WHERE USER_ID=" + userId;
        status = execUpdate(query);
        return status;
    }

    @Override
    public int getQuantity(int userId, int productId) {
        int quantity = -1; // if this is returned then error happened
        try (Statement statement = DbConnection.getStatement()){
            String query = "SELECT QUANTITY FROM SHOPPING_CART WHERE PRODUCT_ID=" + productId + " and USER_ID=" + userId;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                quantity = resultSet.getInt("QUANTITY");
            } else {
                quantity = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    private Status execUpdate(String query) {
        Status status;
        try (Statement statement = DbConnection.getStatement()){
            int rowCount = statement.executeUpdate(query);
            if (rowCount > 0) {
                status = Status.OK;
            } else {
                status = Status.NOTOK;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.ERROR;
        }
        return status;
    }

    public static void main(String[] args) {
        CartDaoImpl cartDao = new CartDaoImpl();
        //System.out.println(cartDao.deleteProduct(10, 15));
        //System.out.println(cartDao.addProduct(1, 1));
        //System.out.println(cartDao.userItemCount(10));
        //System.out.println(cartDao.getQuantity(1, 5));
    }
}
//dont forget to remove main 