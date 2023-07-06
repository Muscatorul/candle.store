package com.cande.store.repository;

import com.cande.store.dto.ShoppingCartCandleDto;
import com.cande.store.dto.ShoppingCartDto;
import com.cande.store.entity.ChosenCandle;
import com.cande.store.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

        ShoppingCart findByUserEmail(String email);
}


