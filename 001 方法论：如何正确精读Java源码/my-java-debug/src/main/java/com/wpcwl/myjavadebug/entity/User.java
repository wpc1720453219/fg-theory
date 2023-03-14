package com.wpcwl.myjavadebug.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String address;
    private List<Account> accounts;

}
