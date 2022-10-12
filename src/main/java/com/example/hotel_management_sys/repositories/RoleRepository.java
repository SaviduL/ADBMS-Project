package com.example.hotel_management_sys.repositories;

import com.example.hotel_management_sys.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
