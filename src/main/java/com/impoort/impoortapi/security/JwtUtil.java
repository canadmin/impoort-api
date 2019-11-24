package com.impoort.impoortapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {
    public static final String SECRET = "impoort-api-secret-key-canburakyusufhasan";
    public static final String SECRET_CLIENT = "x-key";
    public static final String SECRET_KEY = "123";

    public static String generateToken(String email) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", email);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String header = "Bearer " + jwt;

        return header;
    }

    public static void validateToken(String token) {
        try {
            Map<String, Object> icerik = Jwts.parser()
                    .setSigningKey(SECRET.trim())
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
        } catch (Exception e) {
            throw new IllegalStateException("Gecersiz Token");
        }
    }
}
