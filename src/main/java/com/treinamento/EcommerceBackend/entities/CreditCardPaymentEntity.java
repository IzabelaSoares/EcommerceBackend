package com.treinamento.EcommerceBackend.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.treinamento.EcommerceBackend.entities.enums.StatusPaymentEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CreditCardPayment")
@JsonTypeName("creditCard")
public class CreditCardPaymentEntity extends PaymentEntity{
    private static final long serialVersionUID = 1;
    private Integer installmentsNumber;

    public CreditCardPaymentEntity() {

    }

    public CreditCardPaymentEntity(StatusPaymentEnum status, OrderEntity order, Integer installmentsNumber) {
        super(status, order);
        this.installmentsNumber = installmentsNumber;
    }

    public CreditCardPaymentEntity(OrderEntity order, Integer installmentsNumber) {
        super(order);
        this.installmentsNumber = installmentsNumber;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }
}
