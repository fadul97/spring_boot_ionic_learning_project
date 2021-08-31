package com.leonardofadul.springboot.ionic.learning.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BankBilletPayment extends Payment{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public BankBilletPayment(){
    }

    public BankBilletPayment(Integer id, PaymentState paymentState, Pedido pedido, Date dueDate, Date paymentDate) {
        super(id, paymentState, pedido);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
