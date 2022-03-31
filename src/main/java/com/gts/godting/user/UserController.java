package com.gts.godting.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class UserController {

    @Value("${auth.login}")
    private String loginUrl;

    @GetMapping("/auth/kakao")
    public void loginByKakao(HttpServletResponse response) throws IOException {
        response.sendRedirect(loginUrl + "/kakao");
    }

    @GetMapping("/auth/naver")
    public void loginByNaver(HttpServletResponse response) throws IOException {
        response.sendRedirect(loginUrl + "/naver");
    }

    @GetMapping("/auth/google")
    public void loginByGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect(loginUrl + "/google");
    }

}
