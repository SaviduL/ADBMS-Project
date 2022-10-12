package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_sys.entities.Transactions;
import com.example.hotel_management_sys.repositories.TransactionsRepository;

import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private TransactionsRepository transactionsRepository;

    public float getTransactionAmount()
    {
        Transactions transaction =  transactionsRepository.getTransaction();
        if(Objects.nonNull(transaction))
        {
            return transaction.getTotal_amount();
        }
        else
            return 0;
    }
}
