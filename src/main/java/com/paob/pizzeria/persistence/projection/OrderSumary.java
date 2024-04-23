package com.paob.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSumary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
