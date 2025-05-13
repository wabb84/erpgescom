package com.produccion.gescom.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.produccion.gescom.security.jwt.JwtUtils;
import com.produccion.gescom.services.impl.UserDetailsServiceImpl;

//import com.produccion.consultaenlineach.security.jwt.JwtUtils;
//import com.produccion.consultaenlineach.service.UserDetailsServiceImpl;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
    	//logger.info("Jwt 1_1");
        String tokenHeader = request.getHeader("Authorization");
        //logger.info("Jwt 1_2");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
        	//logger.info("Jwt 1_3");
            String token = tokenHeader.substring(7);
            
            if(jwtUtils.isTokenValid(token)){
                String username = jwtUtils.getUsernameFromToken(token);
                
                
                
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
