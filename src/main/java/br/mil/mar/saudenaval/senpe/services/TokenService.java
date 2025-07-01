package br.mil.mar.saudenaval.senpe.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import br.mil.mar.saudenaval.senpe.entities.User;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    
    //private final String secret ="M@rinha20";

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("security")
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationTime())
                    .withClaim("perfil",user.getPerfil().name())
                    .withClaim("name",user.getName())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generate token: ",e);
        }
    }

    public String generateBiometriaToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("security")
                    .withSubject(user.getUsername())
                    .withExpiresAt(getBiometriaExpirationTime())
                    .withClaim("perfil",user.getPerfil().name())
                    .withClaim("name",user.getName())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generate token: ",e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("security")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
           return "";
        }
    }


    private Instant getExpirationTime(){
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant getBiometriaExpirationTime(){
        return LocalDateTime.now().plusYears(100).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateTokenToRecoverPassword(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("security")
                    .withSubject(user.getId())
                    .withExpiresAt(getExpirationTime())
                    .withClaim("username",user.getUsername())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generate token: ",e);
        }
    }


    public String validateTokenExpiration(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("security")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            // Verificar se o token já expirou
            Date expiresAt = decodedJWT.getExpiresAt();
            if (expiresAt != null && expiresAt.before(new Date())) {
                return "Expired"; // Token expirou
            }

            return decodedJWT.getSubject(); // Retorna o id de usuário se o token não expirou
        } catch (JWTVerificationException e) {
            return ""; // Token inválido
        }
    }


}
