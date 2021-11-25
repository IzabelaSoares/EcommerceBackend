package com.treinamento.EcommerceBackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private PaymentEntity payment;

    @ManyToOne
    @JoinColumn(name = "IdClient", foreignKey = @ForeignKey(name = "FkClient"))
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "IdAddress", foreignKey = @ForeignKey(name = "FkAddress"))
    private AddressEntity address;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItemEntity> orderItemList = new HashSet<>();

    public OrderEntity() {
    }

    public OrderEntity(Date instant, ClientEntity client, AddressEntity address) {
        this.id = null;
        this.instant = instant;
        this.client = client;
        this.address = address;
    }

    public Double getTotal() {
        return orderItemList.stream().mapToDouble(x -> x.getSubTotal()).sum();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Set<OrderItemEntity> getOrderItemList() {
        return orderItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
