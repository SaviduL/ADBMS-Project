package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_sys.entities.Inventory;
import com.example.hotel_management_sys.repositories.InventoryRepository;

import java.util.List;

@Service
public class MealsService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getMealsForOwner()
    {
        return inventoryRepository.getMealsForOwner();
    }
}
