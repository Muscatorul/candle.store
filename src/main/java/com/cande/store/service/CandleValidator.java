package com.cande.store.service;

import com.cande.store.dto.CandleDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class CandleValidator {
    public void validate(CandleDto candleDto, BindingResult bindingResult){
        if (candleDto.getTitle().isEmpty()){
            FieldError fieldError=new FieldError("candleDto","title","Pleas fill mandatory fields!");
            bindingResult.addError(fieldError);
        }

    }
}
