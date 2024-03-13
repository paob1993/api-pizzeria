package com.paob.pizzeria.persistence.repository;

import com.paob.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrdersRepository extends ListCrudRepository<OrderEntity, Integer> {
}
