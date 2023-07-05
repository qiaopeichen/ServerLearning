package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.obj.db.Account;
import com.example.demo.obj.msg.HttpStatus;
import com.example.demo.obj.msg.SRet;
import com.example.demo.obj.msg.server.SLoginMsg;
import com.example.demo.obj.msg.server.SRegMsg;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.xml.transform.Result;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginService {

    //bean管理是指（1）spring创建对象 （2）spring注入属性。当我们在将一个类上标注@Service或者@Controller或@Component或@Repository注解之后，spring的组件扫描就会自动发现它，并且会将其初始化为spring应用上下文中的bean。 而且初始化是根据无参构造函数。
    // 在这里 Autowired注解的作用是 自动创建对象
    @Autowired
    AccountMapper accountMapper;

    // 注册
    public SRet<SRegMsg> reg(String account, String password){
        // 使用正则表达式判断账号格式是否正确
        // 字母、数字、特殊符号组成，账号字数限制5-20个字符 特殊符号仅限 @ $ ^ ! ~ , . *
        if (checkAccount(account)==false){
            return SRet.error(HttpStatus.REG_ACCOUNT_FORM_ERROR.getCode(), HttpStatus.REG_ACCOUNT_FORM_ERROR.getMsg());
        }

        // 使用正则表达式判断密码格式是否正确
        // 字母、数字、特殊符号组成，账号字数限制8-16个字符 特殊符号仅限 @ $ ^ ! ~ , . *
        if (checkPassword(password)==false){
            return SRet.error(HttpStatus.REG_PASSWORD_FORM_ERROR.getCode(), HttpStatus.REG_PASSWORD_FORM_ERROR.getMsg());
        }

        // md5加密密码
        String md5pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        // 向数据库插入数据，响应插入结果
        Account newAccountObj = new Account();
        newAccountObj.setAccount(account);
        newAccountObj.setPassword(md5pwd);
        int insert = accountMapper.insert(newAccountObj);
        // 成功插入一行数据，insert返回值就是1，代表成功插入1行
        if (insert!=1){
            return SRet.error(HttpStatus.REG_DUPLICATE_ACCOUNT_ERROR.getCode(), HttpStatus.REG_DUPLICATE_ACCOUNT_ERROR.getMsg());
        }

        return SRet.success("创建账号成功");
    }


    // 登录
    public SRet<SLoginMsg> login(String account, String password){
        SLoginMsg sLoginMsg = new SLoginMsg();

        // 使用mybatisplus从account表查找账号为"参数账号"的一条数据
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        Account accountObj = accountMapper.selectOne(wrapper);
        if (accountObj==null){
            // 没有这个账号
            return SRet.error(HttpStatus.LOGIN_FIND_ACCOUNT_ERROR.getCode(), HttpStatus.LOGIN_FIND_ACCOUNT_ERROR.getMsg());
        }

        // 加密密码后比较
        String md5pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (md5pwd.equals(accountObj.getPassword())){
            // 登录成功，生成token，返回成功消息给客户端
            String token = JwtUtil.createToken(accountObj.getId(), accountObj.getAccount());
            sLoginMsg.setToken(token);
            return SRet.success(sLoginMsg);
        }

        // 密码错误
        return SRet.error(HttpStatus.LOGIN_PASSWORD_ERROR.getCode(), HttpStatus.LOGIN_PASSWORD_ERROR.getMsg());
    }

    //校验创建事件页面账号格式
    private boolean checkAccount(String accountNumber) {
        String valicateAccount="^[\\w@\\$\\^!~,.\\*]{5,20}+$";
        Pattern pattern = Pattern.compile(valicateAccount);
        Matcher matcher = pattern.matcher(accountNumber);
        boolean matches = matcher.matches();
        if(matches) {
            return true;
        }else {
            return false;
        }
    }

    //密码校验：
    private boolean checkPassword(String passWord) {
        String valicatePassword="^[\\w@\\$\\^!~,.\\*]{8,16}+$";
        Pattern pattern = Pattern.compile(valicatePassword);
        Matcher matcher = pattern.matcher(passWord);
        boolean matches = matcher.matches();
        if(matches) {
            return true;
        }else {
            return false;
        }
    }
}
