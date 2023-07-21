package com.example.demo.controller;
import com.example.demo.obj.msg.SRet;
import com.example.demo.obj.msg.client.CLoginMsg;
import com.example.demo.obj.msg.client.CRegMsg;
import com.example.demo.obj.msg.server.SLoginMsg;
import com.example.demo.obj.msg.server.SRegMsg;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    //postman请求头 需要增加 Content-Type application/json

    //当设置@RequestBody 后， postman调试post需要把参数写在请求的body里
//    {
//        "account":"hello777",
//            "password":"hello888"
//    }
    @PostMapping("/reg")
    public SRet<SRegMsg> reg(@RequestBody CRegMsg cRegMsg){
        SRet<SRegMsg> reg = loginService.reg(cRegMsg.getAccount(), cRegMsg.getPassword());
        return reg;
    }

    @PostMapping("/login")
    public SRet<SLoginMsg> login(@RequestBody CLoginMsg cLoginMsg){
        SRet<SLoginMsg> reg = loginService.login(cLoginMsg.getAccount(), cLoginMsg.getPassword());
        return reg;
    }
}
