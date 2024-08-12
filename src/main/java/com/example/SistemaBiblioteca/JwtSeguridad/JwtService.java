package com.example.SistemaBiblioteca.JwtSeguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";


    public String obtenerToken(UserDetails usuario){
        return obtenerToken(new HashMap<>(), usuario);
    }

    private String obtenerToken(Map<String, Object> extraClaims, UserDetails usuario) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(obtenerClave(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key obtenerClave() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String obtenerUsuario(String token) {
        return obtenerPeticion(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = obtenerUsuario(token);
        return (username.equals(userDetails.getUsername()) && !TokenExpirado(token));
    }

    private Claims obtenerTodasPeticiones(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(obtenerClave())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T obtenerPeticion(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = obtenerTodasPeticiones(token);
        return claimsResolver.apply(claims);
    }
    private Date obtenerExpiracion(String token) {
        return obtenerPeticion(token, Claims::getExpiration);
    }

    private boolean TokenExpirado(String token) {
        return obtenerExpiracion(token).before(new Date());
    }

}
