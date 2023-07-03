package com.cande.store.service;

import com.cande.store.dto.CandleDto;
import com.cande.store.entity.Candle;
import com.cande.store.mapper.CandleMapper;
import com.cande.store.repository.CandleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CandleService {
    @Autowired
    CandleRepository candleRepository;
@Autowired
private CandleMapper candleMapper;
    public List<Candle>listAllCandels(){
        return candleRepository.findAll();

    }
    public void saveCandle(CandleDto candleDto, MultipartFile file) throws IOException {
    Candle candle=candleMapper.candleMapper(candleDto,file);
candleRepository.save(candle);

    }
}
