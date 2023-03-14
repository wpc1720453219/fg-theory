package com.wpcwl.myjavadebug.service;

import com.wpcwl.myjavadebug.entity.Account;
import com.wpcwl.myjavadebug.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Slf4j
@Service
public class UserService {

    public User findAll() {
        User user = new User();
        user.setId(1);
        user.setUsername("孙悟空");
        user.setAddress("花果山");
        Account account = new Account();
        account.setId(1);
        account.setAccountName("A银行");
        account.setMoney(new BigDecimal(10001.00));
        account.setUserId(1);
        ArrayList accounts = new ArrayList<Account>();
        accounts.add(account);
        user.setAccounts(accounts);
        return user;
    }
}
