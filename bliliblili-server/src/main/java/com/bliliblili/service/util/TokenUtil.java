package com.bliliblili.service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bliliblili.service.exception.ConditionException;

import java.util.Calendar;
import java.util.Date;

public class TokenUtil {
    //签发者
    private static final String ISSUER = "bliliblili";

    /**
     * jwt生成token
      * @param userId
     * @return
     * @throws Exception
     */
    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //TODO
        calendar.add(Calendar.MINUTE, 120);
        return JWT.create().withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    /**
     * token 校验
     *
     * @param token
     * @return
     */
    public static Long verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException e) {
            throw new ConditionException("555","token过期!");
        }catch(Exception e){
            throw new ConditionException("非法用户token!");
        }
    }

}
