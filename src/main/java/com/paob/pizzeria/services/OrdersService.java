package com.paob.pizzeria.services;

import com.paob.pizzeria.persistence.entity.OrderEntity;
import com.paob.pizzeria.persistence.projection.OrderSumary;
import com.paob.pizzeria.persistence.repository.OrdersRepository;
import com.paob.pizzeria.services.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrderEntity> getAll() {
        return this.ordersRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.ordersRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.ordersRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String customerId) {
        return this.ordersRepository.findCustomerOrders(customerId);
    }

    public OrderSumary getSummary(int orderId) {
        return this.ordersRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return this.ordersRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
