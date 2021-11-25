package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.DTO.CategoryDTO;
import com.treinamento.EcommerceBackend.DTO.ProductDTO;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
import com.treinamento.EcommerceBackend.resources.utils.Url;
import com.treinamento.EcommerceBackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll(){
        List<ProductEntity> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Integer id){
        ProductEntity product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String nameUrl,
            @RequestParam(value = "listId", defaultValue = "") String categoryUrl,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        String name = Url.decodeString(nameUrl);
        List<Integer> categoryListId = Url.decodeIntList(categoryUrl);
        Page<ProductEntity> productList = productService.search(name, categoryListId, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> productDTOList = productList.map(product -> new ProductDTO(product));
        return ResponseEntity.ok().body(productDTOList);
    }
}
