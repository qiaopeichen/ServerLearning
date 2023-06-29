package com.example.demo.controller;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.obj.db.Account;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/test")
    @ApiOperation("测试接口")
    public String test(){
        Account account = accountMapper.selectById(7);
        String ret = "hello test ok：" + account.getAccount();
        log.info("info输出：" +ret);
        log.error("error输出：" +ret);
        return ret;
    }

//    @GetMapping("verifyJWT")
//    public String verifyJWT(String token){
//        DecodedJWT decodedJWT = JwtUtil.verify(token);
//        Integer id = decodedJWT.getClaim("id").asInt();
//        String account = decodedJWT.getClaim("account").asString();
//        return "verifyJWT test ok: id=" + id + " account=" + account;
//    }

    @GetMapping("/add")
    public String add(){
        Account account = new Account();
        account.setAccount("zhangsan");
        account.setPassword("zsmima123");
//        try{
        accountMapper.insert(account);
//        }catch (DataAccessException exception){
//            log.info("aktest :" + exception.getMessage() + exception.getCause());
//        }
        return "";
    }

    @GetMapping("/del")
    public String del(Integer id){
        int i = accountMapper.deleteById(id);
        return "" + i;
    }

    @GetMapping("/modify")
    public String modify(Integer id, String newPwd){
        Account account = accountMapper.selectById(id);
        account.setPassword(newPwd);
        accountMapper.updateById(account);
        return "";
    }

    @GetMapping("/select")
    public String select(Integer id){
        Account account = accountMapper.selectById(id);
//        return account.getAccount();
        return account.toString();
    }

    @GetMapping("/find")
    public List<Account> find(){
//        return account.getAccount();
        return accountMapper.selectList(null);
    }
}