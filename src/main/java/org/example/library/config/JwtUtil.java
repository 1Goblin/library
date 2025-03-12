//package org.example.library.config;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import java.util.Date;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtUtil {
//
//    private static final String SECRET_KEY = "MySecretKeyForJWTTokenThatIsVeryLongAndSecure";
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
//
//    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//
//    public String generateToken(String username, String role) {
//        return Jwts.builder()
//            .setSubject(username)
//            .claim("role", role)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(key, SignatureAlgorithm.HS256)
//            .compact();
//    }
//
//    public Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//            .setSigningKey(key)
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public String extractRole(String token) {
//        return extractClaims(token).get("role", String.class);
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            extractClaims(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}