package com.example.hotel_management_sys.repositories;

import com.example.hotel_management_sys.entities.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionsRepository extends CrudRepository<Transactions, LocalDateTime> {
    //get last transactions
    @Query(value = "call get_last_transactions_amount()",nativeQuery = true)
    Transactions getTransaction();
}
