package com.paob.pizzeria.web.controller;

import com.paob.pizzeria.persistence.entity.PizzaEntity;
import com.paob.pizzeria.services.PizzasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzasController {
    private final PizzasService pizzasService;

    @Autowired
    public PizzasController(PizzasService pizzasService) {
        this.pizzasService = pizzasService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(this.pizzasService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id) {
        return ResponseEntity.ok(this.pizzasService.get(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getId() == null || !this.pizzasService.exists(pizza.getId())) {
            return ResponseEntity.ok(this.pizzasService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getId() != null && this.pizzasService.exists(pizza.getId())) {
            return ResponseEntity.ok(this.pizzasService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (this.pizzasService.exists(id)) {
            this.pizzasService.delete(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
