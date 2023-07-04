package com.cande.store.repository;

import com.cande.store.entity.ChosenCandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChosenCandleRepository  extends JpaRepository <ChosenCandle,Integer> {
}
