package com.leonardofadul.springboot.ionic.learning.project.domain;

import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment{

    private Integer installments;

    public CardPayment(){
    }

    public CardPayment(Integer id, PaymentState paymentState, Pedido pedido, Integer installments) {
        super(id, paymentState, pedido);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
