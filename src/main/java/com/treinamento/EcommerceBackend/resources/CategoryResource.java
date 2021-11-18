package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAll(){
        Category cat1 = new Category(1, "Informática");
        Category cat2 = new Category(1, "Escritório");

        List<Category> list = new ArrayList<>();
        list.add(cat1);
        list.add(cat2);
        return list;
    }

}
