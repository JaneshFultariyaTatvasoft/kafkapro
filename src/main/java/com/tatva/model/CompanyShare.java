package com.tatva.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CompanyShare {

    private long companyId;
    private Date date;

    private BigDecimal currentValue;
}
