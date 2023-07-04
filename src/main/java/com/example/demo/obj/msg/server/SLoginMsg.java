package com.example.demo.obj.msg.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录消息返回结构")
public class SLoginMsg {
    @ApiModelProperty("返回值 0-成功 1-失败")
    private Integer ret;
    private String token;
}
