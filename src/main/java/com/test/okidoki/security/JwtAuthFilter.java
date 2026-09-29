package com.test.okidoki.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import jakarta.servlet.http.Cookie;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = "D0P4M1N45UP3$r53$cR3$T4OkiDoK19AB";
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        Cookie[] cookies = request.getCookies();
        log.info("Cookies: {}", cookies != null ? cookies.length : 0);
        if (cookies != null) {
            log.info("Processing cookies for JWT authentication");
            for (Cookie cookie : cookies) {
                log.info("Cookie name: {}, value: {}", cookie.getName(), cookie.getValue());
                if ("jwt".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    try {
                        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                        Claims claims = Jwts.parser()
                                .verifyWith(secretKey)
                                .build()
                                .parseSignedClaims(token)
                                .getPayload();

                        String username = claims.getSubject();
                        List<String> roles = claims.get("roles", List.class);

                        log.info("JWT claims - username: {}, roles: {}", username, roles);

                        if (username != null && roles != null) {
                            var authorities = roles.stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                    .collect(Collectors.toList());


                            log.info(authorities.toString());

                            UsernamePasswordAuthenticationToken auth =
                                    new UsernamePasswordAuthenticationToken(username, null, authorities);

                            log.info(auth.toString());

                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    } catch (Exception e) {
                        SecurityContextHolder.clearContext();
                    }


                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

/**if (header != null && header.startsWith("Bearer ")) {
String token = header.replace("Bearer ", "");
           try {
SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
Claims claims = Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
       .getPayload();

String username = claims.getSubject();
List<String> roles = claims.get("roles", List.class);

                if (username != null && roles != null) {
var authorities = roles.stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(username, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                        } catch (Exception e) {
        SecurityContextHolder.clearContext();
            }
                    }
**/