package com.cande.store.service;

import com.cande.store.dto.ShoppingCartCandleDto;
import com.cande.store.dto.ShoppingCartDto;
import com.cande.store.entity.ChosenCandle;
import com.cande.store.entity.ShoppingCart;
import com.cande.store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    public ShoppingCartDto getShoppingCartByUserEmail(String email) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserEmail(email);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        Double subTotal = 0.0;

        for (ChosenCandle chosenCandle : shoppingCart.getChosenCandles()) {
            ShoppingCartCandleDto shoppingCartCandleDto = ShoppingCartCandleDto
                    .builder()
                    .name(chosenCandle.getCandle().getTitle())
                    .price(String.valueOf(chosenCandle.getCandle().getPrice()))
                    .quantity(String.valueOf(chosenCandle.getChosenQuantity()))
                    .build();

            Double auxPrice = chosenCandle.getChosenQuantity() * chosenCandle.getCandle().getPrice();

            subTotal += auxPrice;
            shoppingCartCandleDto.setTotal(String.valueOf(auxPrice));
            shoppingCartDto.add(shoppingCartCandleDto);
        }


        shoppingCartDto.setSubTotal(String.valueOf(subTotal));
        shoppingCartDto.setTotal(String.valueOf(subTotal) + 50);

        return shoppingCartDto;
    }











}
