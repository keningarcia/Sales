package com.keningarcia.security;
//Clase S5

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Profile(value = {"dev", "qa", "prod"})
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(tokenHeader != null) {
            if(tokenHeader.startsWith("Bearer ") || tokenHeader.startsWith("bearer")) {
                //jwtToken = tokenHeader.split("Bearer ")[1];
                final int TOKEN_POSITION = 7;
                jwtToken = tokenHeader.substring(TOKEN_POSITION);

                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                }catch (Exception e) {
                    request.setAttribute("exception", e.getMessage());
                }
            }
        }
        if(username != null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
