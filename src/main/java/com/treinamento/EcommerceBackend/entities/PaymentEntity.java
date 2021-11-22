package com.treinamento.EcommerceBackend.entities;

import com.treinamento.EcommerceBackend.entities.enums.StatusPaymentEnum;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Payment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    private Integer id;
    private Integer status;

    @OneToOne
    @JoinColumn(name = "IdOrder", foreignKey = @ForeignKey(name = "FkOrder"))
    @MapsId
    private OrderEntity order;

    public PaymentEntity() {
    }

    public PaymentEntity(StatusPaymentEnum status, OrderEntity order) {
        this.id = null;
        this.status = status.getNumber();
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusPaymentEnum getStatus() {
        return StatusPaymentEnum.toEnum(status);
    }

    public void setStatus(StatusPaymentEnum status) {
        this.status = status.getNumber();
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
