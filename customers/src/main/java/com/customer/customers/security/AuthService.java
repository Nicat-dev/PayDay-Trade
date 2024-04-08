package com.customer.customers.security;

import com.customer.customers.model.dto.JwtDto;
import com.customer.customers.model.request.LoginRequest;
import com.customer.customers.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    UserDetailsService userDetailsService;

    public JwtDto login(LoginRequest request) {
        authenticate(request.username(), request.password());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        String token = jwtUtil.doGenerateToken(userDetails);
        return new JwtDto(token);
    }

    private void authenticate(String username,String password){
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("user is disabled", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("bad credentials", e);
        }

    }

}
