package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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