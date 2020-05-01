package com.task.jwt.utils;

import com.task.jwt.userDetails.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration.time}")
    private long EXPIRATION_TIME;

    static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String extractEmailId(String token){
        logger.info("Extracting user email from JWT");
        return extractClaim(token, Claims::getSubject);
    }

    public String extractLastName(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
        logger.info("Extracting user lastName from JWT");
        return claims.get("lastName", String.class);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        logger.info("Inside generateToken method");
        Map<String, Object> claims = new HashMap<>();
        claims.put("lastName", userDetails.getJwtUserModel().getLastName());
        return createToken(claims, userDetails.getJwtUserModel().getEmail());
    }

    private String createToken(Map<String,Object> claims, String subject){
        logger.info("Building Json Web Token");
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        logger.info("Inside validateToken method");
        final String email = extractEmailId(token);
        logger.info("Email extracted from jwt token : " + email);
        logger.info("Email of user : " + userDetails.getJwtUserModel().getEmail());
        final String lastName = extractLastName(token);
        logger.info("User lastName extracted from jwt token : " + lastName);
        logger.info("lastName of User : " + userDetails.getJwtUserModel().getLastName());
        return (lastName.equals(userDetails.getJwtUserModel().getLastName())
                && email.equals(userDetails.getJwtUserModel().getEmail())
                && !isTokenExpired(token));
    }


}
