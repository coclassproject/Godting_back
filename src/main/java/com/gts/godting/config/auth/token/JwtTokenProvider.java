package com.gts.godting.config.auth.token;

import com.gts.godting.config.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@PropertySource("classpath:application-dev.properties")
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private long accessTokenValidTime = 1000L * 60L * 60L; // 1시간
    private long refreshTokenValidTime = 1000L * 60L * 60L * 24L * 30L; // 30일(1달)

    private final UserDetailsServiceImpl userDetailsService;

    public JwtToken createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);

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

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}