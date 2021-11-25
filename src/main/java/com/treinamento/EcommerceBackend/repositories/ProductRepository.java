package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Transactional(readOnly = true)
    Page<ProductEntity>findDistinctByNameContainingAndCategoryListIn(String name,List<CategoryEntity> categoryEntityList, Pageable pageRequest);

    /* Nesta opção se constrói na mão uma consulta com JPQL para buscar os produtos pelo nome e os ids de categorias disponiveis

    @Query("SELECT DISTINCT product FROM ProductEntity product INNER JOIN product.categoryList categoryObj " +
            "WHERE product.name LIKE %:name% AND categoryObj IN :categoryList")
    Page<ProductEntity> searchProductByCategoryList
            (@Param("name") String name, @Param("categoryList") List<CategoryEntity> categoryEntityList, Pageable pageRequest);
    */
}
