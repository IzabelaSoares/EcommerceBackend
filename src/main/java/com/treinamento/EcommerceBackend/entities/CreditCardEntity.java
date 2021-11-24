package com.treinamento.EcommerceBackend.entities;

import com.treinamento.EcommerceBackend.entities.enums.StatusPaymentEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CreditCardPayment")
public class CreditCardEntity extends PaymentEntity{
    private static final long serialVersionUID = 1;
    private Integer installmentsNumber;

    public CreditCardEntity() {

    }

    public CreditCardEntity(StatusPaymentEnum status, OrderEntity order, Integer installmentsNumber) {
        super(status, order);
        this.installmentsNumber = installmentsNumber;
    }

    public CreditCardEntity(OrderEntity order, Integer installmentsNumber) {
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
