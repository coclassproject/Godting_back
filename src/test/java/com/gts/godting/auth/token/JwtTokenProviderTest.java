package com.gts.godting.auth.token;

import com.gts.godting.config.AppConfig;
import com.gts.godting.config.auth.token.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JwtTokenProviderTest {
    JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        jwtTokenProvider = appConfig.jwtTokenProvider();
    }

    @Test
    @DisplayName("JWT 토큰 생성 테스트")
    void createToken() {
        String email = "test@test.com";

        String token = jwtTokenProvider.createToken(email);

        System.out.println("token = " + token);
    }
}
