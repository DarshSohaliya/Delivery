package com.example.Delivery.config;

import com.example.Delivery.service.JwtUserDetailService;
import com.example.Delivery.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    JwtUserDetailService jwtUserDetailService;


//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest httpServletRequest){
//        String path = httpServletRequest.getServletPath();
//        return path.startsWith("/api/auth");
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(authHeader != null  && authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);
            username = jwtUtils.extractUsername(jwtToken);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);

            if(jwtUtils.validation(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Authenticated: " + username);
            }
        }
        else{
            System.out.println("JWT VALIDATION FILDED");
        }
        filterChain.doFilter(request,response);
    }
}
