package com.gts.godting.auth.token;

import com.gts.godting.config.AppConfig;
import com.gts.godting.config.auth.token.JwtToken;
import com.gts.godting.config.auth.token.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JwtTokenProviderTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    JwtTokenProvider jwtTokenProvider = ac.getBean(JwtTokenProvider.class);


    @Test
    @DisplayName("JWT 토큰 생성 테스트")
    void createToken() {
        String email = "test@test.com";
        JwtToken token = jwtTokenProvider.createToken(email);

        System.out.println("token = " + token);

        System.out.println(jwtTokenProvider.verifyToken(token.getAccessToken()));
        System.out.println(jwtTokenProvider.verifyToken(token.getRefreshToken()));
    }
}
