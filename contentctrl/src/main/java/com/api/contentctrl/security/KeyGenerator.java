package com.api.contentctrl.security;

import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.security.Key;

public class KeyGenerator {
    public static void geretKey(String[] args) {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256); // Gera uma chave segura
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Nova SECRET_KEY (Base64): " + base64Key);
    }
}

