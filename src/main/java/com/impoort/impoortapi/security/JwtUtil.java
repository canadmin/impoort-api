package com.impoort.impoortapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {
    static final String SECRET = "impoort-api-secret-key-canburakyusufhasan";

    public static String generateToken(String email) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", email);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt;
    }

    public static void validateToken(String token) {
        try {
            Map<String, Object> icerik = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        }catch (Exception e){
            throw new IllegalStateException("Gecersiz Token. "+e.getMessage());
        }
    }
}
