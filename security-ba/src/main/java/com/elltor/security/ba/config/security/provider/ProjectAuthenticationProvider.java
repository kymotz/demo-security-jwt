package com.elltor.security.ba.config.security.provider;

import com.elltor.security.ba.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProjectAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsServiceImpl userDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            throw new BadCredentialsException("认证信息空");
        }
        UserDetails user = userDetails.loadUserByUsername(authentication.getName());
        boolean matches = passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword());
        authentication.setAuthenticated(matches);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
