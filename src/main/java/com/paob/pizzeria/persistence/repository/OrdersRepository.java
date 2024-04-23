package com.paob.pizzeria.persistence.repository;

import com.paob.pizzeria.persistence.entity.OrderEntity;
import com.paob.pizzeria.persistence.projection.OrderSumary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
    @Query(value = "SELECT * FROM pizzas_orders WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String customerId);
    @Query(value = """
            SELECT po.id AS idOrder, cu.name as customerName, po.`date` AS orderDate,
                   po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames
            FROM pizzas_orders po
                INNER JOIN customers cu ON po.id_customer = cu.id
                INNER JOIN orders_items oi ON po.id = oi.id_order
                INNER JOIN pizzas pi ON oi.id_pizza = pi.id
            WHERE po.id = :orderId
            GROUP BY po.id, cu.name, po.date, po.total
        """, nativeQuery = true)
    OrderSumary findSummary(@Param("orderId") int orderId);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);
}
