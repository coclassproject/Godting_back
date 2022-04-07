package com.gts.godting.config.auth.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@PropertySource("classpath:application-dev.properties")
@Slf4j
public class JwtTokenProvider {

    @Value("${Jwt-Secret-Key}")
    private String secretKey;

    private long accessTokenValidTime = 1000L * 60L * 60L; // 1시간
    private long refreshTokenValidTime = 1000L * 60L * 60L * 24L * 30L; // 4주(1달)

    public JwtToken createToken(String uid, String email) {
        Claims claims = Jwts.claims().setSubject(uid);
        claims.put("email", email);

        Date date = new Date();

        log.info("토큰 생성");
        return new JwtToken(
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(date)
                        .setExpiration(new Date(date.getTime() + accessTokenValidTime))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact(),
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(date)
                        .setExpiration(new Date(date.getTime() + refreshTokenValidTime))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact());

    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return claimsJws.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
}