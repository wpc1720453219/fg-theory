package com.wpcwl.myjavadebug.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Account implements Serializable {
    private Integer id;
    private String accountName;
    private BigDecimal money;
    private Integer userId;
}
