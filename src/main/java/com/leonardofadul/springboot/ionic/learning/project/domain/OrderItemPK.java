package com.leonardofadul.springboot.ionic.learning.project.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "orderrequest_id")
    private OrderRequest orderRequest;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(orderRequest, that.orderRequest) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderRequest, product);
    }
}
