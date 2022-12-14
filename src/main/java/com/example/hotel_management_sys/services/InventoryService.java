package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_sys.entities.Inventory;
import com.example.hotel_management_sys.repositories.InventoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean addInventory(Integer p_id, Integer qty, Float unit_price, LocalDateTime date) {
        inventoryRepository.addInventory(p_id,qty,unit_price,date);
        return true;
    }

    public List<Inventory> getAllInventory()
    {
        return (List<Inventory>) inventoryRepository.findAll();
    }


    public boolean deleteInventory(Integer id) {
        inventoryRepository.deleteInventory(id);
        return true;
    }

    public boolean restoreInventory(Integer id) {
        Inventory inventory;
        if (inventoryRepository.findById(id).isPresent())
        {
            inventory = inventoryRepository.findById(id).get();
            inventory.setIs_deleted(0);
            inventoryRepository.save(inventory);
            return  true;
        }
        return false;
    }

    public List<Inventory> getInventoryForCustomers()
    {
        return inventoryRepository.getInventoryForCustomer();
    }

    public Integer getProductID(Integer id)
    {
        Inventory inventory = inventoryRepository.findById(id).get();
        return inventory.getProduct_id();
    }

}
