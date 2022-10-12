package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_sys.entities.Category;
import com.example.hotel_management_sys.entities.Product;
import com.example.hotel_management_sys.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    public List<Category> getAllCategories()
    {
        return categoryRepository.getAllCategories();
    }

    public String getCategoryName(Integer id)
    {
        Product product = productService.getProduct(id);
        Integer cat_id = product.getCategory_id();
        Category category = categoryRepository.findById(cat_id).get();
        return  category.getType();
    }
}
