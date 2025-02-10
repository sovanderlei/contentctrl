package com.api.contentctrl.security;
  
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    //private static final String SECRET_KEY = "seuSegredoSuperSecretoSuperSeguro123!@#"; // Must be at least 32 characters long 
    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public String generateToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Expira em 1 hora
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS512)
                .compact();

        System.out.println("Token Gerado: " + token);
        return token;
    }


    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}

