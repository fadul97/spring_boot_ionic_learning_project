package com.leonardofadul.springboot.ionic.learning.project.domain;

import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Id
    private Integer id;
    private Integer paymentState;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private OrderRequest orderRequest;

    public Payment(){
    }

    public Payment(Integer id, PaymentState paymentState, OrderRequest orderRequest) {
        this.id = id;
        this.paymentState = paymentState.getCod();
        this.orderRequest = orderRequest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return PaymentState.toEnum(paymentState);
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState.getCod();
    }

    public OrderRequest getOrder() {
        return orderRequest;
    }

    public void setOrder(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
