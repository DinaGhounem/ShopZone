package jtech.shopzone.model.dal.dao.impl;

import java.sql.*;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtech.shopzone.model.dal.DbConnection;

public class ProductDaoImpl implements ProductDao {

    Connection connection = null;
    static int productStop = 0;

    public ProductDaoImpl() {
        connection = DbConnection.getConnection();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts() {
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        String query = "SELECT * from PRODUCTS_INFO where DELETED_FLG=1";
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
                product.setDeletedFlg(resultSet.getInt("DELETED_FLG"));
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

    public ArrayList<ProductsInfoEntity> getAvaliableProducts() {
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        String query = "SELECT * from PRODUCTS_INFO where DELETED_FLG=1 and QUANTITY!=0";
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
                product.setDeletedFlg(resultSet.getInt("DELETED_FLG"));
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
        String query = "SELECT * from PRODUCTS_INFO where CATEGORY_ID='" + categoryId + "' and DELETED_FLG=1";
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
                product.setDeletedFlg(resultSet.getInt("DELETED_FLG"));
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
        String query = "SELECT * from PRODUCTS_INFO where DELETED_FLG=1 and PRICE between " + minPrice + " and " + maxPrice;
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
                product.setDeletedFlg(resultSet.getInt("DELETED_FLG"));
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
                preparedStatement = DbConnection.getPreparedStatement("insert into PRODUCTS_INFO values(?,?,?,?,?,?,?,1)");
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
            preparedStatement = DbConnection.getPreparedStatement("update PRODUCTS_INFO set deleted_flg = 0 where product_id='" + productId + "'");

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

    /**
     * This method returns full data of a product with its product id
     *
     * @param productID
     * @return whole product info as product table
     */
    @Override
    public ProductsInfoEntity getProductInfo(int productID) {
        // Return object
        ProductsInfoEntity product = null;

        // Build product select statement
        String productQuery = "select * from PRODUCTS_INFO where PRODUCT_ID = " + productID;

        // get new statement from the connection
        try (Statement statement = DbConnection.getStatement()) {

            // execute select statement and get results
            ResultSet resultSet = statement.executeQuery(productQuery);

            // if result exists do some work
            if (resultSet.next()) {

                /* get each column value into a variable */
                int PRODUCT_ID = resultSet.getInt("PRODUCT_ID");
                String PRODUCT_NAME = resultSet.getString("PRODUCT_NAME");
                int PRICE = resultSet.getInt("PRICE");
                int QUANTITY = resultSet.getInt("QUANTITY");
                String DESCRIPTION = resultSet.getString("DESCRIPTION");
                int CATEGORY_ID = resultSet.getInt("CATEGORY_ID");
                String IMG = resultSet.getString("IMG");

                // Construct a new product variable
                product = new ProductsInfoEntity();
                product.setProductId(PRODUCT_ID);
                product.setProductName(PRODUCT_NAME);
                product.setPrice(PRICE);
                product.setQuantity(QUANTITY);
                product.setDescription(DESCRIPTION);
                product.setCategoryId(CATEGORY_ID);
                product.setImg(IMG);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /* @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range) {
          ArrayList<ProductsInfoEntity> products = new ArrayList<>();
      //  String query = "SELECT * from PRODUCTS_INFO  limit"+(range-1)*8+", "+range*8+"";
       String query = "SELECT * from (select * from PRODUCTS_INFO order by product_id) where ROWNUM <= "+((range)*8)+" and ROWNUM > "+((range-1)*8);
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
        
    }*/
    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range) {
        ArrayList<ProductsInfoEntity> allProducts = getAvaliableProducts();
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        for (int i = ((range - 1) * 8), j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfoEntity product = allProducts.get(j);
            if (product.getQuantity() == 0 || product.getDeletedFlg() == 0) {
                i--;

            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange2(int range) {
        ArrayList<ProductsInfoEntity> allProducts = getProducts();
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        for (int i = ((range - 1) * 8), j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfoEntity product = allProducts.get(j);
            if (product.getDeletedFlg() == 0) {
                i--;

            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, int categoryId) {
        ArrayList<ProductsInfoEntity> allProducts = getProducts(categoryId);
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        for (int i = (range - 1) * 8, j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfoEntity product = allProducts.get(j);
            if (product.getQuantity() == 0 || product.getDeletedFlg() == 0) {
                i--;;
            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, double minPrice, double maxPrice) {
        ArrayList<ProductsInfoEntity> allProducts = getProducts(minPrice, maxPrice);
        ArrayList<ProductsInfoEntity> products = new ArrayList<>();
        for (int i = (range - 1) * 8, j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfoEntity product = allProducts.get(j);
            if (product.getQuantity() == 0 || product.getDeletedFlg() == 0) {
                i--;
            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public int getProductCount() {
        
        int productCount = 0;
        String query = "select count(*) from PRODUCTS_INFO  where DELETED_FLG=1 and quantity!=0";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                productCount = resultSet.getInt(1);
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
        return productCount;
    }
 @Override
    public int getProductCount2() {
        /*int productCount =getProducts().size();
        return productCount;*/
        int productCount = 0;
        String query = "select count(*) from PRODUCTS_INFO  where DELETED_FLG=1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                productCount = resultSet.getInt(1);
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
        return productCount;
    }
    @Override
    public double getMaxmimumPrice() {

        String query = "SELECT max(price) from PRODUCTS_INFO";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double maxPrice = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                maxPrice = resultSet.getDouble(1);

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
        return maxPrice;
    }

    //just for test ^_^
    public static void main(String[] args) {
        ProductsInfoEntity product = new ProductsInfoEntity();
//        product.setProductId(2);
//        product.setProductName("nutall");
//        product.setCategoryId(1);
//        product.setDescription("choclate");
//        product.setPrice(100);
//        product.setQuantity(100);
//        product.setImg("product_5.png");
        ProductDaoImpl pdi = new ProductDaoImpl();
//         //ArrayList<ProductsInfoEntity>products=pdi.getProducts(10,100);
//       /* for (ProductsInfoEntity product1 : products) {
//              System.out.println(product1.getPrice());
//        }*/
        System.out.println(pdi.getProductCount());
    }

}
