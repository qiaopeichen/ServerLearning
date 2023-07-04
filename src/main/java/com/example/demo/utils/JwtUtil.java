package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.*;

public class JwtUtil {
    // 秘钥
    private static final String JWT_SECRET = "vcf&*sgh$ff";

    // 根据用户id与username生成token
    public static String createToken(int id, String account){
        // 签发时间
        Calendar ins = Calendar.getInstance();
        ins.add(Calendar.SECOND, 10);

        // 秘钥加密方式
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

        String token = JWT.create()
                .withClaim("id", id)
                .withClaim("account", account)
                .withExpiresAt(ins.getTime())
                .sign(algorithm);

        return token;
    }

    // 获取token信息
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(JWT_SECRET)).build().verify(token);
    }

}