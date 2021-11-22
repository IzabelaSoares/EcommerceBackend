package com.treinamento.EcommerceBackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treinamento.EcommerceBackend.entities.enums.StatusPaymentEnum;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BankSlipPayment")
public class BankSlipPaymentEntity extends PaymentEntity{
    private static final long serialVersionUID = 1;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public BankSlipPaymentEntity() {
    }

    public BankSlipPaymentEntity(StatusPaymentEnum status, OrderEntity order, Date dueDate, Date paymentDate) {
        super(status, order);
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
