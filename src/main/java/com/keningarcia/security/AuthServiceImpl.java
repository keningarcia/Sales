package com.keningarcia.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl {

    public boolean hasAccess(String path){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        log.info(username);

        auth.getAuthorities().forEach(e -> log.info(e.getAuthority()));
        return true;
    }
}
