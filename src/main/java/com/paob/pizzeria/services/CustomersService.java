package com.paob.pizzeria.services;

import com.paob.pizzeria.persistence.entity.CustomerEntity;
import com.paob.pizzeria.persistence.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {
    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public CustomerEntity findByPhone(String phone) {
        return this.customersRepository.findByPhone(phone);
    }
}
