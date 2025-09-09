package com.ecotech.config;

import com.ecotech.model.User;
import com.ecotech.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String email = jwtUtil.extractEmail(token);
            if (email != null) {
                userRepository.findByEmail(email).ifPresent(user -> {
                    req.setAttribute("authUser", user);
                });
            }
        }
        chain.doFilter(req, res);
    }
}
