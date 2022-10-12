package com.example.hotel_management_sys.repositories;

import com.example.hotel_management_sys.entities.Orders_log;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;

@Repository
public interface Orders_logRepository extends CrudRepository<Orders_log, Integer> {

    // add order log  procedure
    @Transactional
    @Procedure(procedureName = "add_orders_log")
    void addOrdersLog(Integer item, Integer user_id, Integer quantity, Float total, Date ordered);
}
