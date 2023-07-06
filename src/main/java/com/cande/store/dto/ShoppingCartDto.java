package com.cande.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ShoppingCartDto {
    private String SubTotal;
    private String total;
    List<ShoppingCartCandleDto>candles = new ArrayList<>();

    public void add(ShoppingCartCandleDto shoppingCartCandleDto){
        candles.add(shoppingCartCandleDto);
    }
}
