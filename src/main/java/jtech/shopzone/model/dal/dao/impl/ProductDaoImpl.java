package jtech.shopzone.model.dal.dao.impl;

import java.sql.*;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jtech.shopzone.controller.util.ProductsInfoEntityAdapter;
import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.bean.ProductsInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * @author Muhammed Mahrous & Dina
 */
public class ProductDaoImpl implements ProductDao {

    private Session instanceSession = MySessionFactory.getMySessionFactory().getSession();
    static int productStop = 0;

    @Override
    public synchronized ArrayList<ProductsInfo> getProducts() {
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ArrayList<ProductsInfo> productsInfos = null;
        try {
            List<ProductsInfo> fromProductsInfo = session.createQuery("FROM ProductsInfo p WHERE p.deletedFlg = 1").list();
            productsInfos = new ArrayList<>(fromProductsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productsInfos;
    }

    public synchronized ArrayList<ProductsInfo> getAvailableProducts() {
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ArrayList<ProductsInfo> productsInfos = null;
        try {
            List<ProductsInfo> fromProductsInfo = session.createQuery("FROM ProductsInfo p WHERE p.deletedFlg = 1 and p.quantity!=0").list();
            productsInfos = new ArrayList<>(fromProductsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productsInfos;
    }

    @Override
    public synchronized ArrayList<ProductsInfo> getProducts(int categoryId) {
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ArrayList<ProductsInfo> productsInfos = null;
        try {
            List<ProductsInfo> fromProductsInfo = session
                    .createQuery("FROM ProductsInfo p WHERE p.deletedFlg = 1 and p.productsCategory.categoryId = :categoryId")
                    .setParameter("categoryId", categoryId).list();
            productsInfos = new ArrayList<>(fromProductsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productsInfos;
    }

    @Override
    public synchronized ArrayList<ProductsInfo> getProducts(double minPrice, double maxPrice) {
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ArrayList<ProductsInfo> productsInfos = null;
        try {
            List<ProductsInfo> fromProductsInfo = session
                    .createQuery("FROM ProductsInfo p WHERE p.deletedFlg = 1 and p.price between :min and :max")
                    .setParameter("min", (long) minPrice)
                    .setParameter("max", (long) maxPrice)
                    .list();
            productsInfos = new ArrayList<>(fromProductsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productsInfos;
    }

    @Override
    public synchronized ArrayList<ProductsInfo> getProductsBTWRange(int range) {
        ArrayList<ProductsInfo> allProducts = getAvailableProducts();
        ArrayList<ProductsInfo> products = new ArrayList<>();
        for (int i = ((range - 1) * 8), j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfo product = allProducts.get(j);
            if (product.getQuantity() == 0 || product.getDeletedFlg() == 0) {
                i--;

            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public synchronized ArrayList<ProductsInfo> getProductsBTWRange(int range, int categoryId) {
        ArrayList<ProductsInfo> allProducts = getProducts(categoryId);
        ArrayList<ProductsInfo> products = new ArrayList<>();
        for (int i = (range - 1) * 8, j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfo product = allProducts.get(j);
            if (product.getQuantity() == 0 || product.getDeletedFlg() == 0) {
                i--;
            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public ArrayList<ProductsInfo> getProductsBTWRange(int range, double minPrice, double maxPrice) {
        ArrayList<ProductsInfo> allProducts = getProducts(minPrice, maxPrice);
        ArrayList<ProductsInfo> products = new ArrayList<>();
        for (int i = (range - 1) * 8, j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfo product = allProducts.get(j);
            if (product.getQuantity() == 0 || product.getDeletedFlg() == 0) {
                i--;
            } else {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Status addProduct(ProductsInfo product) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        try {
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
            status = Status.OK;
        } catch (Exception e) {
            session.getTransaction().rollback();
            status = Status.ERROR;
        }
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
    public Status updateProduct(ProductsInfo product) {
        return addProduct(product);
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
        } else {
            status = Status.NOTOK;
        }
        session.close();
        return status;
    }

    @Override
    public ProductsInfo getProductInfo(int productID) {
        Session session = MySessionFactory.getMySessionFactory().getSession();
        ProductsInfo productsInfo = session.get(ProductsInfo.class, productID);
        session.close();
        return productsInfo;
    }
    @Override
    public synchronized int getProductCount() {
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
        return (double) maxPrice;
    }

    @Override
    public synchronized ArrayList<ProductsInfo> getProductsBTWRange2(int range) {
        ArrayList<ProductsInfo> allProducts = getProducts();
        ArrayList<ProductsInfo> products = new ArrayList<>();
        for (int i = ((range - 1) * 8), j = i; i < range * 8 && j < allProducts.size(); i++, j++) {
            ProductsInfo product = allProducts.get(j);
            if (product.getDeletedFlg() == 0) {
                i--;

            } else {
                products.add(product);
            }
        }
        return products;
    }

}
