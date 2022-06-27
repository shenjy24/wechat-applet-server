package com.jonas.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT(JSON WEB TOKEN) 工具
 *
 * @author shenjy 2022/06/27
 */
@Slf4j
public class JwtUtil {

    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();

    /**
     * HS256 对称加密
     *
     * @param openid 用户标识
     * @return
     */
    public static String generateToken(String openid) throws JOSEException {
        Map<String, Object> payload = new HashMap<String, Object>() {{
            put("openid", openid);
        }};

        return generateToken(payload);
    }

    /**
     * HS256 对称加密
     *
     * @param payloadMap
     * @return
     */
    public static String generateToken(Map<String, Object> payloadMap) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = new Payload(payloadMap);
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        JWSSigner jwsSigner = new MACSigner(secret);
        jwsObject.sign(jwsSigner);

        return jwsObject.serialize();
    }

    /**
     * 验证token
     *
     * @param token JWT
     * @return token验证是否通过
     * @throws ParseException
     * @throws JOSEException
     */
    public static boolean checkToken(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        return jwsObject.verify(jwsVerifier);
    }

    public static Map<String, Object> getPayload(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        if (jwsObject.verify(jwsVerifier)) {
            Payload payload = jwsObject.getPayload();
            return payload.toJSONObject();
        }
        return new HashMap<>();
    }

    public static void main(String[] args) {
        try {
            String token = JwtUtil.generateToken("123");
            System.out.println("token=" + token);
            if (null != token) {
                Map<String, Object> result = getPayload(token);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
