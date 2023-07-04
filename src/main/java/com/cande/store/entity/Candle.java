package com.cande.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter

public class Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Double price;
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "file_id",referencedColumnName = "id")
    private FileCover fileCover;

    @OneToMany(mappedBy = "candle")
    private List<ChosenCandle> chosenCandles;
}
