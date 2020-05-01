package com.projet7.ZuulGateway.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.projet7.ZuulGateway.services.UserPrinciple;

import java.util.Date;

@Component
public class JwtProvider {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="projet7@ocr";
    public static final long EXPIRATION=4*3600*1000;//4h
    public static final String HEADER_PREFIX="Bearer ";

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);


    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();

    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;


    }
}
