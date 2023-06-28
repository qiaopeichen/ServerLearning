package com.example.demo.obj.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
// 注意1：指定实体类对象与数据库表的关联关系
@TableName("account")
public class Account {
    // 注意2：属性的命名要与数据库中字段的命名相同（mybatisplus会自动开启驼峰命名转下划线命名的转换）
    // 注意3：id字段需要注释标记其id生成方式
    @TableId(type= IdType.AUTO)
    private Integer id;
    // 账号
    private String account;
    // 密码
    private String password;
}