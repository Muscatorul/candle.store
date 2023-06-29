package com.cande.store.repository;

import com.cande.store.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandleRepository extends JpaRepository<Candle,Integer> {
}
