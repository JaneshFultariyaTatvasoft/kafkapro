package com.tatva.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IPOListing {
    private Long id;
    private String name;
    private String owner;
    private BigDecimal sahrePrice;
    private Boolean isListed = true;
}
