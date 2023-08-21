package com.example.book.utils;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import java.util.Map;

/**
 * @author songsiyuan
 * @date 2023/8/21 09 20
 * discription
 */
public class JwtUtil {
    // 过期时间1440分钟(24小时)
    private static final long EXPIRE_TIME = 1440 * 60 * 1000;

    // jwt私钥
    private static final String SECRET = "song si yuan";

    /**
     * 生成jwt字符串，24小时后过期  JWT(json web token)
     * @param userId 用户ID
     * */
    public static String sign(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(userId)
                    //五分钟后token过期
                    .withExpiresAt(date)
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token
     * @param token jwt字符串
     * @return true:校验通过，false:校验失败
     * */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm  = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withClaim("username, username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
//            throw new RuntimeException("token 无效，请重新获取");
            return false;
        }
    }

    /**
     * 根据token获取userId
     * @param token jwt字符串
     * @return
     * */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取自定义数据info
     * @param token jwt字符串
     * @return
     * */
    public static Map<String, Object> getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("info").asMap();
        }catch (JWTDecodeException e) {
            return null;
        }
    }
}


