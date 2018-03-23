package jtech.shopzone.model.dal.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jtech.shopzone.model.dal.DbConnection;
import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.dao.CategoryDao;
import jtech.shopzone.model.entity.ProductCategoryEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * @author Mahrous
 */
public class CategoryDaoImpl implements CategoryDao {
    private Session instanceSession = MySessionFactory.getMySessionFactory().getSession();

    @Override
    public ArrayList<ProductsCategory> getCategories() {
        ArrayList<ProductsCategory> productsCategories = null;
        try {
            Query query = instanceSession.createQuery("FROM ProductsCategory");
            productsCategories = new ArrayList<>(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsCategories;
    }

    @Override
    public int getCategoryByName(String categoryName) {
        int categoryId;
        try {
            Query query = instanceSession
                    .createQuery("SELECT p.categoryId FROM ProductsCategory p where p.categoryName= :name")
                    .setParameter("name", categoryName);
            List<Long> result = query.list();
            if (result != null) {
                categoryId = result.get(0).intValue();
            } else {
                categoryId = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            categoryId = -2;
        }
        return categoryId;
    }

    @Override
    public String getCategortById(int categoryId) {
        String categoryName = null;
        try {
            Query query = instanceSession
                    .createQuery("SELECT p.categoryName FROM ProductsCategory p where p.categoryId= :id")
                    .setParameter("id", categoryId);
            List<String> result = query.list();
            if (result != null) {
                categoryName = result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }

    @Override
    public Status addCategory(ProductCategoryEntity category) {
        Status status;
        Session session = MySessionFactory.getMySessionFactory().getSession();
        try {
            session.beginTransaction();
            ProductsCategory productsCategory = new ProductsCategory();
            productsCategory.setCategoryName(category.getCategoryName());
            session.persist(productsCategory);
            session.getTransaction().commit();
            status = Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            status = Status.ERROR;

        }
        return status;

    }

}
