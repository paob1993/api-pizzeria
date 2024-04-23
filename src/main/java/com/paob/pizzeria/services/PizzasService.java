package com.paob.pizzeria.services;

import com.paob.pizzeria.persistence.entity.PizzaEntity;
import com.paob.pizzeria.persistence.repository.PizzasPagSortRepository;
import com.paob.pizzeria.persistence.repository.PizzasRepository;
import com.paob.pizzeria.services.dto.UpdatePizzaPriceDto;
import com.paob.pizzeria.services.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzasService {
    private final PizzasRepository pizzasRepository;
    private final PizzasPagSortRepository pizzasPagSortRepository;

    @Autowired
    public PizzasService(PizzasRepository pizzasRepository, PizzasPagSortRepository pizzasPagSortRepository) {
        this.pizzasRepository = pizzasRepository;
        this.pizzasPagSortRepository = pizzasPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzasPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        System.out.println(this.pizzasRepository.countByVeganTrue());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzasPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzasRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzasRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzasRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzasRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La pizza no existe"));
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

    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzasRepository.updatePrice(dto);
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }

    public boolean exists(int id) {
        return this.pizzasRepository.existsById(id);
    }
}
