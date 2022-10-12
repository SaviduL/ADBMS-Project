package com.example.hotelmanagetg380.repositories;

import com.example.hotelmanagetg380.entities.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer>{

    //insert inventory procedure
    @Transactional
    @Procedure(procedureName = "inventory_create")
    void addInventory(Integer p_id,Integer qty, Float unit_price, LocalDateTime date);

    //delete inventory procedure
    @Transactional
    @Procedure(procedureName = "inventory_delete")
    void deleteInventory(Integer inventory_id);

    //meals for owner view
    @Query(value = "SELECT  *FROM meals_for_owner ",nativeQuery = true)
    List<Inventory> getMealsForOwner();

    //inventory for customers
    @Query(value = "SELECT  *FROM inventory_for_customers ",nativeQuery = true)
    List<Inventory> getInventoryForCustomer();


}
