package com.example.hotelmanagetg380.repositories;

import com.example.hotelmanagetg380.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
