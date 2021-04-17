package com.imadelfetouh.adminservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidateJWTToken {

    private static final Logger logger = Logger.getLogger(ValidateJWTToken.class.getName());

    private static final ValidateJWTToken validateJWTToken = new ValidateJWTToken();

    public static ValidateJWTToken getInstance(){
        return validateJWTToken;
    }

    private Jws<Claims> getClaims(String jwtToken){
        Key key = SecretKeyGenerator.getInstance().getKey();

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken);

    }

    public String getUserData(String jwtToken){
        try {
            return (String) getClaims(jwtToken).getBody().get("userdata");
        }
        catch (JwtException e){
            logger.log(Level.ALL, e.getMessage());
            return null;
        }
    }
}
