package com.gts.godting.config.auth.token;


import com.gts.godting.config.auth.UserDetailsImpl;
import com.gts.godting.config.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToken = jwtTokenProvider.resolveToken((HttpServletRequest) request, "ACCESS");
        String refreshToken = null;

        try {
            if(accessToken != null ) {
                UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwtTokenProvider.getUserEmail(accessToken));
                if (jwtTokenProvider.verifyToken(accessToken)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // refresh 가져오기
                }
            }
        } catch (ExpiredJwtException e) {
            log.info("accessToken : ExpiredJwtException");
        } catch (Exception e) {
            log.info("Exception");
        }


        try {
            // refresh 검증
        } catch (ExpiredJwtException e) {
            log.info("refreshToken : ExpiredJwtException");
        }

        chain.doFilter(request, response);
    }
}
