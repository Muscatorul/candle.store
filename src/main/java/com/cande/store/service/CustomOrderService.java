package com.cande.store.service;

import com.cande.store.entity.ChosenCandle;
import com.cande.store.entity.CustomerOrder;
import com.cande.store.entity.ShoppingCart;
import com.cande.store.entity.User;
import com.cande.store.repository.ChosenCandleRepository;
import com.cande.store.repository.CustomOrderRepository;
import com.cande.store.repository.ShoppingCartRepository;
import com.cande.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomOrderRepository customOrderRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ChosenCandleRepository chosenCandleRepository;
    public void addCustomerOrder(String email,String shippingAddress){
        Optional<User>user=userRepository.findByEmail(email);
        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setUser(user.get());
        customerOrder.setShippingAddress(shippingAddress);
        customOrderRepository.save(customerOrder);
        ShoppingCart shoppingCart= shoppingCartRepository.findByUserEmail(email);
        for(ChosenCandle chosenCandle:shoppingCart.getChosenCandles()){
            chosenCandle.setShoppingCart(null);
            chosenCandle.setCustomerOrder(customerOrder);
            chosenCandleRepository.save(chosenCandle);


        }
    }
}
