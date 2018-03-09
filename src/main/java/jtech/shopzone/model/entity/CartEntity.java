package jtech.shopzone.model.entity;

public class CartEntity {
    private Integer quantity;
    private ProductsInfoEntity productsInfoEntity;
    private StockStatus stockStatus;

    public CartEntity() {
    }

    public CartEntity(Integer quantity, ProductsInfoEntity productsInfoEntity, StockStatus stockStatus) {
        this.quantity = quantity;
        this.productsInfoEntity = productsInfoEntity;
        this.stockStatus = stockStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductsInfoEntity getProductsInfoEntity() {
        return productsInfoEntity;
    }

    public void setProductsInfoEntity(ProductsInfoEntity productsInfoEntity) {
        this.productsInfoEntity = productsInfoEntity;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "quantity=" + quantity +
                ", productsInfoEntity=" + productsInfoEntity +
                ", stockStatus=" + stockStatus +
                '}';
    }
}
