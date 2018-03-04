package jtech.shopzone.model.entity;

public class CartEntity {
    private Integer quantity;
    private ProductsInfoEntity productsInfoEntity;

    public CartEntity() {
    }

    public CartEntity(Integer quantity, ProductsInfoEntity productsInfoEntity) {
        this.quantity = quantity;
        this.productsInfoEntity = productsInfoEntity;
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

    @Override
    public String toString() {
        return "CartEntity{" +
                "quantity=" + quantity +
                ", productsInfoEntity=" + productsInfoEntity +
                '}';
    }
}
