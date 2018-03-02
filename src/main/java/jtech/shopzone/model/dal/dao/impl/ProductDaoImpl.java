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
    public Status addProduct(ProductsInfoEntity product) {
        if (product.getQuantity() >= 0) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            int productId = 0;
            try {
                preparedStatement = DbConnection.getPreparedStatement("select nvl(max(product_id),0) from PRODUCTS_INFO");
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    productId = resultSet.getInt(1) + 1;
                } else {
                    return Status.NOTOK;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                return Status.ERROR;

            }
            try {
                preparedStatement = DbConnection.getPreparedStatement("insert into PRODUCTS_INFO values(?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, productId);
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setString(5, product.getDescription());
                preparedStatement.setInt(6, product.getCategoryId());
                preparedStatement.setString(7, product.getImg());

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

    @Override
    public Status deleteProduct(int productId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DbConnection.getPreparedStatement("delete from PRODUCTS_INFO where product_id='" + productId + "'");

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
                DbConnection.closeStatementAndResultSet(preparedStatement, null);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Status updateProduct(ProductsInfoEntity product) {
        if (product.getQuantity() >= 0) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = DbConnection.getPreparedStatement("update PRODUCTS_INFO set product_name=?,price=?,Quantity=?,Description=?,category_id=?,Img=? where product_id=?");
                preparedStatement.setInt(7, product.getProductId());
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.setInt(3, product.getQuantity());
                preparedStatement.setString(4, product.getDescription());
                preparedStatement.setInt(5, product.getCategoryId());
                preparedStatement.setString(6, product.getImg());

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

    @Override
    public int checkProductQuantities(int productId) {
        int quantity = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = DbConnection.getPreparedStatement("select QUANTITY from PRODUCTS_INFO where product_id='" + productId + "'");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("QUANTITY");
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(preparedStatement, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return quantity;
    }

    @Override
    public Status updateProductQuantities(int productId, int quantities) {
        if (quantities >= 0) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = DbConnection.getPreparedStatement("update PRODUCTS_INFO set Quantity=" + quantities + "where product_id=" + productId + "");

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

    //just for test ^_^
    /*public static void main(String[] args) {
        ProductsInfoEntity product = new ProductsInfoEntity();
        product.setProductId(2);
        product.setProductName("nutall");
        product.setCategoryId(1);
        product.setDescription("choclate");
        product.setPrice(100);
        product.setQuantity(100);
        product.setImg("product_5.png");
        ProductDaoImpl pdi = new ProductDaoImpl();
        System.out.println(pdi.updateProductQuantities(1, 60));
    }*/
}
