package com.example.demo.controller;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.obj.db.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/test")
    public String test(){
        Account account = accountMapper.selectById(1);
        String ret = "hello test ok：" + account.getAccount();
        log.info(ret);
        return ret;
    }
}