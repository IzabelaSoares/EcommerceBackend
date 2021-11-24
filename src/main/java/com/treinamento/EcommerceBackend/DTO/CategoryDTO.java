package com.treinamento.EcommerceBackend.DTO;

import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min= 5, max = 80, message = "O nome informado deve conter no mínimo 5 e no máximo 80 caracteres!")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(CategoryEntity category) {
        this.id = category.getId();
        this.name = category.getName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
