package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.ProductController;
import jtech.shopzone.controller.util.ProductCategoryEntityAdaptor;
import jtech.shopzone.controller.util.ProductsInfoEntityAdapter;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.bean.ProductsCategory;
import jtech.shopzone.model.dal.bean.ProductsInfo;
import jtech.shopzone.model.dal.dao.CategoryDao;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.dal.dao.impl.CategoryDaoImpl;
import jtech.shopzone.model.entity.ProductsInfoEntity;

import java.util.ArrayList;

import jtech.shopzone.model.dal.dao.impl.ProductDaoImpl;

public class ProductControllerImpl implements ProductController {

    private ProductDao productDao;
    private CategoryDao categoryDao;

    private ProductControllerImpl() {
        productDao = new ProductDaoImpl();
        categoryDao = new CategoryDaoImpl();
    }

    public static ProductController newInstance() {
        return new ProductControllerImpl();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts() {
        ArrayList<ProductsInfo> productsInfos = productDao.getProducts();
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(int categoryId) {
        ArrayList<ProductsInfo> productsInfos = productDao.getProducts(categoryId);
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProducts(double minPrice, double maxPrice) {
        ArrayList<ProductsInfo> productsInfos = productDao.getProducts(minPrice, maxPrice);
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public Status AddProduct(ProductsInfoEntity product) {
        ProductsCategory productsCategory = categoryDao.getCategortById(product.getCategoryId());
        ProductsInfo productsInfo = ProductsInfoEntityAdapter.fromProductsInfoEntity(product);
        productsInfo.setProductsCategory(productsCategory);
        productsInfo.setDeletedFlg(1);
        return productDao.addProduct(productsInfo);
    }

    @Override
    public Status deleteProduct(int productId) {
        return productDao.deleteProduct(productId);
    }

    @Override
    public Status updateProduct(ProductsInfoEntity product) {
        ProductsInfo productsInfo = productDao.getProductInfo(product.getProductId());
        productsInfo = ProductsInfoEntityAdapter.updateProductsInfo(productsInfo, product);
        return productDao.updateProduct(productsInfo);
    }

    @Override
    public int checkProductQuantities(int productId) {
        return productDao.checkProductQuantities(productId);
    }

    @Override
    public Status updateProductQuantities(int productId, int quantities) {
        return productDao.updateProductQuantities(productId, quantities);
    }

    @Override
    public ProductsInfoEntity getProductInfo(int productID) {
        return ProductsInfoEntityAdapter.toProductsInfo(productDao.getProductInfo(productID));
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range) {
        ArrayList<ProductsInfo> productsInfos = productDao.getProductsBTWRange(range);
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, int categoryId) {
        ArrayList<ProductsInfo> productsInfos = productDao.getProductsBTWRange(range, categoryId);
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange(int range, double minPrice, double maxPrice) {
        ArrayList<ProductsInfo> productsInfos = productDao.getProductsBTWRange(range, minPrice, maxPrice);
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public int getProductCount() {
        return productDao.getProductCount();
    }

    @Override
    public double getMaxmimumPrice() {
        return productDao.getMaxmimumPrice();
    }

    @Override
    public ArrayList<ProductsInfoEntity> getProductsBTWRange2(int range) {
        ArrayList<ProductsInfo> productsInfos = productDao.getProductsBTWRange2(range);
        return ProductsInfoEntityAdapter.toProductsInfoEntities(productsInfos);
    }

    @Override
    public int getProductCount2() {
        return productDao.getProductCount2();
    }

}
