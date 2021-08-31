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
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Pedido getPedido() {
        return pedido;
    }

    public void setOrderRequest(Pedido pedido) {
        this.pedido = pedido;
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
        return Objects.equals(pedido, that.pedido) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, product);
    }
}
