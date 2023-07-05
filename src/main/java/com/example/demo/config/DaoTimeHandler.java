package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@component是spring中的一个注解，它的作用就是实现bean的注入
//配置类，当Account数据插入/更新时@TableField(fill = FieldFill.INSERT) ，会触发这里的代码
@Component
public class DaoTimeHandler implements MetaObjectHandler {

    private String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            String strDate = df.format(new Date());
            setFieldValByName("createTime", strDate, metaObject);
            setFieldValByName("updateTime", strDate, metaObject);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            String strDate = df.format(new Date());
            setFieldValByName("updateTime", strDate, metaObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}