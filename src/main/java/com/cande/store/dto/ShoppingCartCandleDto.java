package com.cande.store.dto;

import jakarta.persistence.AssociationOverride;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Builder
public class ShoppingCartCandleDto {
    private String name;
    private String price;
    private String quantity;
    private String total;



}
