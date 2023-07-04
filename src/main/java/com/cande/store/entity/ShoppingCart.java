package com.cande.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class ShoppingCart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer shoppingCartId;
    @OneToOne
    @JoinColumn
    private User user;
    @OneToMany(mappedBy = "shoppingCart")
    private List <ChosenCandle>chosenCandles;

}
