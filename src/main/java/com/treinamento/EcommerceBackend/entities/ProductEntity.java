package com.treinamento.EcommerceBackend.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @ManyToMany
    @JoinTable(name = "ProdutoCategoria", joinColumns = @JoinColumn(name = "ProdutoId"), foreignKey= @ForeignKey(name="FkProduto"),
            inverseJoinColumns = @JoinColumn(name = "CategoryId"), inverseForeignKey = @ForeignKey(name="FkCategoria"))
    List<CategoryEntity> categoryList = new ArrayList<>();

    public ProductEntity() {
    }

    public ProductEntity(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
