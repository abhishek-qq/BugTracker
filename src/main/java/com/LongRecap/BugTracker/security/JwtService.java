package com.LongRecap.BugTracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {

    private static final String SECRET_KEY = "loanrecapkeyforjwtauthenticationmy";
    private static final long EXPERIATION = 1000*60*60*10;


    private Key getSingingKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return  extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token,Claims :: getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPERIATION))
                .signWith(getSingingKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValid(String token,String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equalsIgnoreCase(username) && isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
