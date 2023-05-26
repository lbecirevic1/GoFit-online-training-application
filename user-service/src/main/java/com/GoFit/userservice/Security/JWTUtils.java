package com.GoFit.userservice.Security;

import com.GoFit.userservice.Models.Role;
import com.GoFit.userservice.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtils {

    @Value("${jjwt.security.key}")
    private String securityKey;

    @Value("${jjwt.token.validity}")
    private int jwtTokenValidity; //in seconds - 10 minutes

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(securityKey.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        final List authorities = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        Long expirationTimeLong = Long.parseLong(String.valueOf(jwtTokenValidity)); //in seconds
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", authorities)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .signWith(key)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
