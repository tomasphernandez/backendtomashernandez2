package com.portfolio.servicio.implementacion;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.portfolio.dto.EntradaUsuario;
import com.portfolio.dto.SalidaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class InicioSesionServicioImplementacion implements com.portfolio.servicio.InicioSesionServicio {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public SalidaUsuario iniciarSesion(EntradaUsuario entradaUsuario) {
        Authentication autenticacion = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(entradaUsuario.getUsuario(), entradaUsuario.getPassword()));

        UserDetails userDetails = (UserDetails) autenticacion.getPrincipal();
        return SalidaUsuario.builder()
                .token(token(userDetails))
                .build();
    }

    private String token(UserDetails userDetails) {
        Algorithm algoritmo = Algorithm.HMAC256("secreto".getBytes());
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(algoritmo);
    }

}
