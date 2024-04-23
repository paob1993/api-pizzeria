package com.paob.pizzeria.services.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int id;
    private double newPrice;
}
