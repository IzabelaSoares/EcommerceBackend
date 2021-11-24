package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.DTO.CategoryDTO;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DataIntegrityException;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity findById(Integer id) {
        Optional<CategoryEntity> user = categoryRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

    public CategoryEntity insert(CategoryEntity category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new DatabaseException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(id);
        }
    }

    public CategoryEntity update(CategoryEntity category) {
        findById(category.getId());
        return categoryRepository.save(category);
    }

    public Page<CategoryEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
    }

    public CategoryEntity convertCategoryDTO(CategoryDTO categoryDTO){
        return new CategoryEntity(categoryDTO.getId(), categoryDTO.getName());
    }

}
