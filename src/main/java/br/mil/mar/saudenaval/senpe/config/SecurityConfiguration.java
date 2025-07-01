package br.mil.mar.saudenaval.senpe.config;


import br.mil.mar.saudenaval.senpe.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf-> csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize->
                        authorize.requestMatchers(HttpMethod.GET,"/users/lists/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/users/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/users/import").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/change-password").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/recover").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/reset-password").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/civil/register").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/vitrines").permitAll()
                                .requestMatchers(HttpMethod.GET,"/solicitacao/historico/{token}").permitAll()
                                .requestMatchers(HttpMethod.GET,"/users/user/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/users/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/solicitacao/request/tomografia").permitAll()
                                .requestMatchers(HttpMethod.POST,"/solicitacao/request/ressonancia").permitAll()
                                .requestMatchers(HttpMethod.GET,"/solicitacao/files/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/solicitacao/{tipo}/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/solicitacao/update").permitAll()
                                .requestMatchers(HttpMethod.GET,"/solicitacao/requests").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/solicitacao/operator/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/solicitacao/resultado").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/solicitacao/filter").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.PUT,"/solicitacao/change/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/solicitacao/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/solicitacao/schedule/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.PUT,"/solicitacao/schedule/cancel/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/solicitacao/search").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/users/operador/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/users/perfil/{nip}/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/users/system").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/notification/send").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/system/ssm").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.PUT,"/users/update/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/sedime").permitAll()
                                .requestMatchers(HttpMethod.GET,"/test").permitAll()
                                .requestMatchers(HttpMethod.POST,"/test/post").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
