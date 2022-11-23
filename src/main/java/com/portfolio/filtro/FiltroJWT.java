package com.portfolio.filtro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class FiltroJWT extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String encabezado = request.getHeader("Authorization");
        if (encabezado != null && encabezado.startsWith("Bearer ")) {
            String jwt = encabezado.substring(7);
            Algorithm algoritmo = Algorithm.HMAC256("secreto".getBytes());
            JWTVerifier verificador = JWT.require(algoritmo).build();
            DecodedJWT tokenDecodificado = verificador.verify(jwt);
            String nombreUsuario = tokenDecodificado.getSubject();
            Authentication autenticacion = new UsernamePasswordAuthenticationToken(nombreUsuario, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(autenticacion);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
