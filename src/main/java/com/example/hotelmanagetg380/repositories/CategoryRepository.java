package com.example.hotelmanagetg380.repositories;

import com.example.hotel_management_sys.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    //get categorizes
    @Query(value = "SELECT c FROM Category c WHERE c.is_deleted=0")
    List<Category> getAllCategories();
}
