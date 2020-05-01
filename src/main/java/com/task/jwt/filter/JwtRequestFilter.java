package com.task.jwt.filter;

import com.task.jwt.userDetails.*;
import com.task.jwt.service.impl.UsrDetailsService;
import com.task.jwt.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UsrDetailsService usrDetailsService;

    static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        String jwt = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("USER_SESSION")) {
                    jwt = c.getValue();
                    break;
                }
            }
        }
        String email = null;
        if (jwt != null) {
            email = jwtUtil.extractEmailId(jwt);
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.usrDetailsService.loadUserByUsername(email);
            logger.info("Validating Json Web Token");
            if (jwtUtil.validateToken(jwt, userDetails)) {
                logger.info("Validation Successful");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                        UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails
                        (new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                logger.info("Validation Unsuccessful");
            }

        }
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "180");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type");
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
