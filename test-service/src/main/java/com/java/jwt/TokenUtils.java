package com.java.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述.
 *
 * @author wuhao
 */
@Slf4j
public class TokenUtils {
  //token秘钥
  private static final String TOKEN_SECRET = "MnavkAqy73Cx3gRpASzAY89JatiELaaG";

  private static final JWTVerifier verifier;

  static {
    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
    verifier = JWT.require(algorithm).build();
    log.info("TokenUtils中verifier={}", verifier);
  }

  /**
   * build token.
   *
   * @param uid uid
   * @return String
   */
  public static String token(String uid) {

    String token = "";
    try {
      //过期时间
      // Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
      //秘钥及加密算法
      Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
      //设置头部信息
      Map<String, Object> header = new HashMap<>();
      header.put("typ", "JWT");
      header.put("alg", "HS256");
      //携带username，password信息，生成签名
      token = JWT.create()
          .withHeader(header)
          .withClaim("uid", uid)
          .sign(algorithm);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return token;
  }

  /**
   * 解码jwt，从而获取信息.
   *
   * @param token token
   * @return DecodDecodedJWTeJwt
   */
  public static DecodedJWT decodedJWT(String token) {
    /*
     * @desc 验证token，通过返回true
     * @params [token]需要校验的串
     */
    try {
      return verifier.verify(token);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static void main(String[] args) {
    String uid = "5f603b665bd3551e86f6c2fc";
    String token = token(uid);
    System.out.println(token);
    DecodedJWT decodedJWT = decodedJWT(
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiI1ZjYwM2I2NjViZDM1NTFlODZmNmMyZmMifQ.G6AzmAMbuujJzJCc_Hki7sUe2PcOlSHFU_RUAGTgQfg");
    System.out.println(decodedJWT);
  }
}
