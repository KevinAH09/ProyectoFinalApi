/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.una.aeropuerto.dto.ParametrosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.service.IParametroService;

/**
 *
 * @author colo7
 */
@Component
public class JwtProvider {
    
    @Autowired
    private IParametroService ParametroService;
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(AuthenticationRequest authenticationRequest) {

        return Jwts.builder().setSubject(authenticationRequest.getCedula())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tiempoExpiracion() * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return false;
        }

    }

    private int tiempoExpiracion() {
        ParametrosDTO parametro =  ParametroService.findByNombreParametro("expiracionToken").get();
        if(parametro != null){
            System.out.println(parametro.getValor());
            return Integer.valueOf(parametro.getValor())*3600;
        }
        return 3600;
    }
}
