package com.java.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/7/12 10:47 上午
 */
public class JwtTest {


  public static void main(String[] args) {
    Jwt jwt = new Jwt("5f603b665bd3551e86f6c2fc");
    Algorithm algorithm = Algorithm.HMAC256("MnavkAqy73Cx3gRpASzAY89JatiELaaG");
    JWTVerifier verifier = JWT.require(algorithm).build();

  }


}

class Jwt {

  private String uid;

  public Jwt(String uid) {
    this.uid = uid;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
}
