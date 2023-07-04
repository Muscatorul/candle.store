package com.cande.store.service;

import com.cande.store.dto.ChosenCandleDto;
import com.cande.store.entity.Candle;
import com.cande.store.entity.ChosenCandle;
import com.cande.store.entity.User;
import com.cande.store.repository.CandleRepository;
import com.cande.store.repository.ChosenCandleRepository;
import com.cande.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChosenCandleService {
    @Autowired
    ChosenCandleRepository chosenCandleRepository;
    @Autowired
    CandleRepository candleRepository;

    @Autowired
    UserRepository userRepository;


    public void addToCart(ChosenCandleDto chosenCandleDto, String candleId, String email) {
        ChosenCandle chosenCandle = buildProduct(chosenCandleDto, candleId, email);
        chosenCandleRepository.save(chosenCandle);

    }

    private ChosenCandle buildProduct(ChosenCandleDto chosenCandleDto, String candleId, String email) {
        ChosenCandle chosenCandle = new ChosenCandle();
        chosenCandle.setChosenQuantity(Integer.parseInt(chosenCandleDto.getQuantity()));

        Optional<Candle> candle = candleRepository.findById(Integer.parseInt(candleId));
        candle.ifPresent(chosenCandle::setCandle);

        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(value -> chosenCandle.setShoppingCart(value.getShoppingCart()));


        return chosenCandle;


    }
}
