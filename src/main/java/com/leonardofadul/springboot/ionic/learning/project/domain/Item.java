package com.leonardofadul.springboot.ionic.learning.project.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Item implements Serializable {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public Item(){
    }

    public Item(OrderRequest orderRequest, Product product, Double discount, Integer quantity, Double price) {
        id.setOrderRequest(orderRequest);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderRequest getOrderRequest(){
        return id.getOrderRequest();
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

