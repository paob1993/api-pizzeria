package com.paob.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders_items")
@IdClass(OrderItemPK.class)
@Getter
@Setter
@NoArgsConstructor
public class OrdenItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "Decimal(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id", insertable = false, updatable = false)
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id", insertable = false, updatable = false)
    private PizzaEntity pizza;
}
