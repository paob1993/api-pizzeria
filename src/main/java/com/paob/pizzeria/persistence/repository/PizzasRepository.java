package com.paob.pizzeria.persistence.repository;

import com.paob.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzasRepository  extends ListCrudRepository<PizzaEntity, Integer> {
}
