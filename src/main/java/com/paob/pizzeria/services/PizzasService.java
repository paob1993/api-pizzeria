package com.paob.pizzeria.services;

import com.paob.pizzeria.persistence.entity.PizzaEntity;
import com.paob.pizzeria.persistence.repository.PizzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzasService {
    private final PizzasRepository pizzasRepository;

    @Autowired
    public PizzasService(PizzasRepository pizzasRepository) {
        this.pizzasRepository = pizzasRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzasRepository.findAll();
    }

    public PizzaEntity get(int id) {
        return this.pizzasRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzasRepository.save(pizza);
    }

    public void delete(int id) {
        this.pizzasRepository.deleteById(id);
    }

    public boolean exists(int id) {
        return this.pizzasRepository.existsById(id);
    }
}
