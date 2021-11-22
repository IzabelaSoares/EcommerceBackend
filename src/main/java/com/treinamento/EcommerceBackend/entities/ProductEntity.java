package com.treinamento.EcommerceBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Product")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToMany
    @JoinTable(name = "ProductCategory", joinColumns = @JoinColumn(name = "IdProduct"), foreignKey= @ForeignKey(name="FkProduct"),
            inverseJoinColumns = @JoinColumn(name = "IdCategory"), inverseForeignKey = @ForeignKey(name="FkCategory"))
    List<CategoryEntity> categoryList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItemEntity> orderItemList = new HashSet<>();

    public ProductEntity() {
    }

    public ProductEntity( String name, Double price) {
        this.id = null;
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<OrderEntity> getOrder(){
        List<OrderEntity> orderList = new ArrayList<>();
        for(OrderItemEntity item : orderItemList){
            orderList.add(item.getOrder());
        }
        return orderList;
    }

    public List<CategoryEntity> getCategoryList() {
        return categoryList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryList=" + categoryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity produto = (ProductEntity) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
