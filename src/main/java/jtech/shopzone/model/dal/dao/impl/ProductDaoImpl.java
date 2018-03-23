package jtech.shopzone.model.dal.dao.impl;

import java.sql.*;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtech.shopzone.controller.util.ProductsInfoEntityAdapter;
import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.bean.ProductsInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductDaoImpl implements ProductDao {

    private Session instanceSession = MySessionFactory.getMySessionFactory().getSession();
    static int productStop = 0;

    @Override
    public ArrayList<ProductsInfoEntity> getProducts() {
        ArrayList<ProductsInfo> products = null;
        Query query = instanceSession.createQuery("FROM ProductsInfo where deletedFlg=1");
        products = new ArrayList<>(query.list());
        ArrayList<ProductsInfoEntity> productsEntity = new ArrayList<>();
        for(int i=0; i<products.size();i++){
           productsEntity.add(ProductsInfoEntityAdapter.toProductsInfo(products.get(i))) ;
        }   
        return productsEntity;
    }

    public ArrayList<ProductsInfoEntity> getAvaliableProducts() {
        ArrayList<ProductsInfo> products = null;
        Query query = instanceSession.createQuery("FROM ProductsInfo where deletedFlg=1 and quantity!=0 ");
        products = new ArrayList<>(query.list());
        ArrayList<ProductsInfoEntity> productsEntity = new ArrayList<>();
        for(int i=0; i<products.size();i++){
           productsEntity.add(ProductsInfoEntityAdapter.toProductsInfo(products.get(i))) ;
        }   
        return productsEntity;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(int categoryId) {
        ArrayList<ProductsInfo> products = null;
        ProductsCategory category = instanceSession.load(ProductsCategory.class, categoryId);
        Query query = instanceSession.createQuery("FROM ProductsInfo where deletedFlg=1 and productsCategory = ?").setParameter(0, category);
        products = new ArrayList<>(query.list());
        ArrayList<ProductsInfoEntity> productsEntity = new ArrayList<>();
        for(int i=0; i<products.size();i++){
           productsEntity.add(ProductsInfoEntityAdapter.toProductsInfo(products.get(i))) ;
        }   
        return productsEntity;
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(double minPrice, double maxPrice) {
        ArrayList<ProductsInfo> products = null;
        Query query = instanceSession.createQuery("FROM ProductsInfo where deletedFlg=1 and price between ? and ?")
                .setParameter(0, (long) minPrice)
                .setParameter(1, (long) maxPrice);
        products = new ArrayList<>(query.list());
        ArrayList<ProductsInfoEntity> productsEntity = new ArrayList<>();
        for(int i=0; i<products.size();i++){
           productsEntity.add(ProductsInfoEntityAdapter.toProductsInfo(products.get(i))) ;
        }   
        return productsEntity;
    }

    @Override
    public Status addProduct(ProductsInfoEntity product) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        if (product.getQuantity() >= 0) {
            ProductsInfo productsInfo = ProductsInfoEntityAdapter.fromProductsInfoEntity(product);
            try {
                session.beginTransaction();
                session.persist(productsInfo);
                session.getTransaction().commit();
                status = Status.OK;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                status = Status.ERROR;

            }
        } else {
            status = Status.NOTOK;
        }
        session.close();
        return status;
    }

    @Override
    public Status deleteProduct(int productId) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ProductsInfo productsInfo = session.load(ProductsInfo.class, productId);
        productsInfo.setDeletedFlg(0);
        productsInfo.setQuantity(0);
        try {
            session.beginTransaction();
            session.persist(productsInfo);
            session.getTransaction().commit();
            status = Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            status = Status.ERROR;
        }
        session.close();
        return status;
    }

    @Override
    public Status updateProduct(ProductsInfoEntity product) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ProductsInfo productsInfo = ProductsInfoEntityAdapter.fromProductsInfoEntity(product);
        productsInfo.setProductId(product.getProductId());
        try {
            session.beginTransaction();
            session.update(productsInfo);
            session.getTransaction().commit();
            status = Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            status = Status.ERROR;
        }
        session.close();
        return status;
    }

    @Override
    public int checkProductQuantities(int productId) {
        int quantity = 0;
        ProductsInfo productsInfo = instanceSession.load(ProductsInfo.class, productId);
        quantity = productsInfo.getQuantity();
        return quantity;
    }

    @Override
    public Status updateProductQuantities(int productId, int quantities) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        if (quantities >= 0) {
            ProductsInfo productsInfo = session.load(ProductsInfo.class, productId);
            productsInfo.setQuantity(quantities);
            try {
                session.beginTransaction();
                session.update(productsInfo);
                session.getTransaction().commit();
                status = Status.OK;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                status = Status.ERROR;
            }
        }
        else{
            status=Status.NOTOK;
        }
        session.close();
        return status;
    }

    /**
     * This method returns full data of a product with its product id
     *
     * @param productID
     * @return whole product info as product table
     */
    @Override
    public ProductsInfoEntity getProductInfo(int productID) {
        ProductsInfoEntity product = null;
        ProductsInfo productsInfo = instanceSession.load(ProductsInfo.class, productID);      
        product = ProductsInfoEntityAdapter.toProductsInfo(productsInfo);
        return product;
    }

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
        long productCount = 0;
        Query query = instanceSession.createQuery("select count(productId) from ProductsInfo where deletedFlg = 1 and quantity!=0");
        productCount = (long) query.list().get(0);
        return (int) productCount;
    }

    @Override
    public int getProductCount2() {
        long productCount = 0;
        Query query = instanceSession.createQuery("select count(productId) from ProductsInfo where deletedFlg = 1");
        productCount = (long) query.list().get(0);
        return (int) productCount;
    }

    @Override
    public double getMaxmimumPrice() {
        long maxPrice = 0;
        Query query = instanceSession.createQuery("select max(price) from ProductsInfo");
        maxPrice = (long) query.list().get(0);
        return (double)maxPrice;
    }
    //just for test ^_^

//    public static void main(String[] args) {
//        ProductsInfoEntity p = new ProductsInfoEntity(4, "product_4", 250, 10, "desc ", 2, null);
//        p.setDeletedFlg(1);
//        ArrayList<ProductsInfo> product;
//        ProductDaoImpl pdi = new ProductDaoImpl();
//
////        pdi.addProduct(p);
////        for (int i = 0; i < product.size(); i++) {
//        System.out.println(pdi.getMaxmimumPrice());
//    }
////    }

}
