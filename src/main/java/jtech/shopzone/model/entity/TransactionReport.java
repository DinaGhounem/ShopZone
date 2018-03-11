package jtech.shopzone.model.entity;

import jtech.shopzone.model.dal.Status;
/**
 * @Author Muhammed Mahrous
 */
public class TransactionReport {
    private CartEntity cartEntity;
    private Status status;
    private String comment;

    public TransactionReport() {
        comment = "";
    }

    public TransactionReport(CartEntity cartEntity, Status status, String comment) {
        this.cartEntity = cartEntity;
        this.status = status;
        this.comment = comment;
    }

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
